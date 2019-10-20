<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20191019115602 extends AbstractMigration
{
    public function getDescription() : string
    {
        return '';
    }

    public function up(Schema $schema) : void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->abortIf($this->connection->getDatabasePlatform()->getName() !== 'mysql', 'Migration can only be executed safely on \'mysql\'.');

        $this->addSql('ALTER TABLE period ADD location LONGTEXT NOT NULL COMMENT \'(DC2Type:array)\', ADD workflow LONGTEXT NOT NULL COMMENT \'(DC2Type:array)\'');
        $this->addSql('ALTER TABLE taxonomy ADD plant_genus VARCHAR(255) NOT NULL, ADD plant_class VARCHAR(255) NOT NULL, ADD plant_family VARCHAR(255) NOT NULL, CHANGE family plant_order VARCHAR(255) NOT NULL');
    }

    public function down(Schema $schema) : void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->abortIf($this->connection->getDatabasePlatform()->getName() !== 'mysql', 'Migration can only be executed safely on \'mysql\'.');

        $this->addSql('ALTER TABLE period DROP location, DROP workflow');
        $this->addSql('ALTER TABLE taxonomy ADD family VARCHAR(255) NOT NULL COLLATE utf8mb4_unicode_ci, DROP plant_order, DROP plant_genus, DROP plant_class, DROP plant_family');
    }
}
