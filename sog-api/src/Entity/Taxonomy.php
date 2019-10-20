<?php

namespace App\Entity;

use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity(repositoryClass="App\Repository\TaxonomyRepository")
 */
class Taxonomy
{
    /**
     * @ORM\Id()
     * @ORM\GeneratedValue()
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @ORM\OneToMany(targetEntity="App\Entity\Crop", mappedBy="taxonomy")
     */
    private $crops;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $plantOrder;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $plantGenus;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $plantSpecies;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $plantFamily;

    public function __construct()
    {
        $this->crops = new ArrayCollection();
    }

    public function getId(): ?int
    {
        return $this->id;
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
            $crop->setTaxonomy($this);
        }

        return $this;
    }

    public function removeCrop(Crop $crop): self
    {
        if ($this->crops->contains($crop)) {
            $this->crops->removeElement($crop);
            // set the owning side to null (unless already changed)
            if ($crop->getTaxonomy() === $this) {
                $crop->setTaxonomy(null);
            }
        }

        return $this;
    }

    public function getPlantOrder(): ?string
    {
        return $this->plantOrder;
    }

    public function setPlantOrder(string $plantOrder): self
    {
        $this->plantOrder = $plantOrder;

        return $this;
    }

    public function getPlantGenus(): ?string
    {
        return $this->plantGenus;
    }

    public function setPlantGenus(string $plantGenus): self
    {
        $this->plantGenus = $plantGenus;

        return $this;
    }

    public function getPlantSpecies(): ?string
    {
        return $this->plantSpecies;
    }

    public function setPlantSpecies(string $plantSpecies): self
    {
        $this->plantSpecies = $plantSpecies;

        return $this;
    }

    public function getPlantFamily(): ?string
    {
        return $this->plantFamily;
    }

    public function setPlantFamily(string $plantFamily): self
    {
        $this->plantFamily = $plantFamily;

        return $this;
    }
}
