<?php

namespace App\Controller\Rest;

use App\Entity\Crop;
use App\Repository\CropRepository;
use FOS\RestBundle\Controller\AbstractFOSRestController;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Validator\Validator\ValidatorInterface;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;

class IndexController extends AbstractFOSRestController
{
    /**
     * @Route("index", methods={"GET"})
     */
    public function index()
    {
        return $this->handleView($this->view('works well', Response::HTTP_OK));
    }


}
