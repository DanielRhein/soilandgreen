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
    public function indexAction()
    {
        $crops = $this->getDoctrine()->getRepository(Crop::class);
        return $this->handleView($this->view($crops->findAll(), 200));
    }

    /**
     * @Route("crop/{id}", methods={"PUT"})
     */
    public function updateCrop($id)
    {
        return $this->handleView($this->view(__METHOD__, 200));

    }

    /**
     * @Route("crop/{id}", methods={"POST"})
     */
    public function createCropAction($id)
    {
        return $this->handleView($this->view(__METHOD__, 200));

    } // "get_user_comments"   [GET] /users/{slug}/comments

    public function getCommentAction($slug, $id)
    {} // "get_user_comment"    [GET] /users/{slug}/comments/{id}

    public function deleteCommentAction($slug, $id)
    {} // "delete_user_comment" [DELETE] /users/{slug}/comments/{id}

    public function newCommentsAction($slug)
    {} // "new_user_comments"   [GET] /users/{slug}/comments/new

    public function editCommentAction($slug, $id)
    {} // "edit_user_comment"   [GET] /users/{slug}/comments/{id}/edit

    public function removeCommentAction($slug, $id)
    {} // "remove_user_comment" [GET] /users/{slug}/comments/{id}/remove
}
