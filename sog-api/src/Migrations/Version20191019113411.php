<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20191019113411 extends AbstractMigration
{
    public function getDescription() : string
    {
        return '';
    }

    public function up(Schema $schema) : void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->abortIf($this->connection->getDatabasePlatform()->getName() !== 'mysql', 'Migration can only be executed safely on \'mysql\'.');

        $this->addSql('CREATE TABLE crop_period (crop_id INT NOT NULL, period_id INT NOT NULL, INDEX IDX_DE60EDA6888579EE (crop_id), INDEX IDX_DE60EDA6EC8B7ADE (period_id), PRIMARY KEY(crop_id, period_id)) DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci ENGINE = InnoDB');
        $this->addSql('ALTER TABLE crop_period ADD CONSTRAINT FK_DE60EDA6888579EE FOREIGN KEY (crop_id) REFERENCES crop (id) ON DELETE CASCADE');
        $this->addSql('ALTER TABLE crop_period ADD CONSTRAINT FK_DE60EDA6EC8B7ADE FOREIGN KEY (period_id) REFERENCES period (id) ON DELETE CASCADE');
    }

    public function down(Schema $schema) : void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->abortIf($this->connection->getDatabasePlatform()->getName() !== 'mysql', 'Migration can only be executed safely on \'mysql\'.');

        $this->addSql('DROP TABLE crop_period');
    }
}
