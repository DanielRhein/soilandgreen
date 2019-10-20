<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20191019114304 extends AbstractMigration
{
    public function getDescription() : string
    {
        return '';
    }

    public function up(Schema $schema) : void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->abortIf($this->connection->getDatabasePlatform()->getName() !== 'mysql', 'Migration can only be executed safely on \'mysql\'.');

        $this->addSql('CREATE TABLE taxonomy (id INT AUTO_INCREMENT NOT NULL, family VARCHAR(255) NOT NULL, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci ENGINE = InnoDB');
        $this->addSql('ALTER TABLE crop ADD taxonomy_id INT DEFAULT NULL');
        $this->addSql('ALTER TABLE crop ADD CONSTRAINT FK_EDC23D9B9557E6F6 FOREIGN KEY (taxonomy_id) REFERENCES taxonomy (id)');
        $this->addSql('CREATE INDEX IDX_EDC23D9B9557E6F6 ON crop (taxonomy_id)');
    }

    public function down(Schema $schema) : void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->abortIf($this->connection->getDatabasePlatform()->getName() !== 'mysql', 'Migration can only be executed safely on \'mysql\'.');

        $this->addSql('ALTER TABLE crop DROP FOREIGN KEY FK_EDC23D9B9557E6F6');
        $this->addSql('DROP TABLE taxonomy');
        $this->addSql('DROP INDEX IDX_EDC23D9B9557E6F6 ON crop');
        $this->addSql('ALTER TABLE crop DROP taxonomy_id');
    }
}
