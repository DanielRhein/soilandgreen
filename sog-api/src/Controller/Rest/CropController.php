<?php

namespace App\Controller\Rest;

use App\Entity\Crop;
use App\Repository\CropRepository;
use FOS\RestBundle\Controller\AbstractFOSRestController;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Flex\Response;

class CropController extends AbstractFOSRestController
{
    /**
     * @Route("crop", name="crop")
     */
    public function index()
    {
        $crops = $this->getDoctrine()->getRepository(Crop::class);
        return $this->handleView($this->view($crops->findAll(), 200));
    }


    public function postCropAction()
    {

    }
}
