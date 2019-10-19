<?php

namespace App\Entity;

use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity(repositoryClass="App\Repository\PeriodRepository")
 */
class Period
{
    /**
     * @ORM\Id()
     * @ORM\GeneratedValue()
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @ORM\Column(type="date")
     */
    private $begin;

    /**
     * @ORM\Column(type="date")
     */
    private $end;

    /**
     * @ORM\ManyToMany(targetEntity="App\Entity\Crop", mappedBy="periods")
     */
    private $crops;

    /**
     * @ORM\Column(type="array")
     */
    private $location = [];

    /**
     * @ORM\Column(type="array")
     */
    private $workflow = [];

    public function __construct()
    {
        $this->crops = new ArrayCollection();
    }

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getBegin(): ?\DateTimeInterface
    {
        return $this->begin;
    }

    public function setBegin(\DateTimeInterface $begin): self
    {
        $this->begin = $begin;

        return $this;
    }

    public function getEnd(): ?\DateTimeInterface
    {
        return $this->end;
    }

    public function setEnd(\DateTimeInterface $end): self
    {
        $this->end = $end;

        return $this;
    }

    /**
     * @return Collection|Crop[]
     */
    public function getCrops(): Collection
    {
        return $this->crops;
    }

    public function addCrop(Crop $crop): self
    {
        if (!$this->crops->contains($crop)) {
            $this->crops[] = $crop;
            $crop->addPeriod($this);
        }

        return $this;
    }

    public function removeCrop(Crop $crop): self
    {
        if ($this->crops->contains($crop)) {
            $this->crops->removeElement($crop);
            $crop->removePeriod($this);
        }

        return $this;
    }

    public function getLocation(): ?array
    {
        return $this->location;
    }

    public function setLocation(array $location): self
    {
        $this->location = $location;

        return $this;
    }

    public function getWorkflow(): ?array
    {
        return $this->workflow;
    }

    public function setWorkflow(array $workflow): self
    {
        $this->workflow = $workflow;

        return $this;
    }
}
