<?php

namespace App\Controller\Rest;

use App\Entity\Crop;
use App\Repository\CropRepository;
use FOS\RestBundle\Controller\AbstractFOSRestController;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Validator\Validator\ValidatorInterface;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;

class CropController extends AbstractFOSRestController
{
    /**
     * @Route("crop", methods={"GET"})
     */
    public function listCrop()
    {
        $crops = $this->getDoctrine()->getRepository(Crop::class);
        return $this->handleView($this->view($crops->findAll(), Response::HTTP_OK));
    }

    /**
     * @Route("crop/{id}", methods={"GET"})
     */
    public function getCrop($id)
    {
        $crop = $this->getDoctrine()
            ->getRepository(Crop::class)
            ->find($id);

        if (!$crop) {
            return $this->handleView($this->view(['error' => true, 'message' => 'No crop found'],
                Response::HTTP_NOT_FOUND));
        }

        return $this->handleView($this->view($crop, Response::HTTP_OK));

    }

    /**
     * @Route("crop/{id}", methods={"PUT"})
     */
    public function updateCrop($id, Request $request, ValidatorInterface $validator)
    {
        $crop = $this->getDoctrine()
            ->getRepository(Crop::class)
            ->find($id);
        $data = json_decode($request->getContent(), true);

        $errors = $validator->validate($crop);
        if (count($errors) > 0) {
            return $this->handleView($this->view(['error' => $errors], Response::HTTP_BAD_REQUEST));

        }

        /** @Todo: Update data */


        return $this->handleView($this->view(['status' => 'ok'], Response::HTTP_CREATED));

    }

    /**
     * @Route("crop", methods={"POST"})
     */
    public function createCrop(Request $request, ValidatorInterface $validator)
    {
        $entityManager = $this->getDoctrine()->getManager();

        $crop = new Crop();
        $data = json_decode($request->getContent(), true);
        $crop->setName($data['name']);
        $crop->setImage($data['image']);
        $crop->setDifficulty($data['difficulty']);
        $crop->setLatinName($data['latin_name']);

        // tell Doctrine you want to (eventually) save the Product (no queries yet)
        $errors = $validator->validate($crop);
        if (count($errors) > 0) {
            return $this->handleView($this->view(['error' => $errors], Response::HTTP_NOT_FOUND));

        }
        $entityManager->persist($crop);

        // actually executes the queries (i.e. the INSERT query)
        $entityManager->flush();

        return $this->handleView($this->view(['status' => 'ok'], Response::HTTP_CREATED));

    }

    /**
     * @Route("crop/{id}", methods={"DELETE"})
     */
    public function deleteCrop($id)
    {
        return $this->handleView($this->view(['try to delete id' => $id, 'notice' => 'not deleted, see code'], Response::HTTP_OK));
        /** @TODO ensure authentification before deleting entries */

        $entityManager = $this->getDoctrine()->getManager();

        $crop = $this->getDoctrine()
            ->getRepository(Crop::class)
            ->find($id);
        try {
            $entityManager->remove($crop);
            $entityManager->flush();
        }catch (\Exception $e)
        {
            return $this->handleView($this->view(['error' => $e->getMessage()], Response::HTTP_NOT_FOUND));

        }
        return $this->handleView($this->view(['status' => 'ok'], Response::HTTP_OK));


    }
}
