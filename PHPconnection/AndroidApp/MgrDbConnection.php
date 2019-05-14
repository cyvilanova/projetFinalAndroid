<?php
/**
 * Fichier: MgrDbConnection.php
 * Auteur: Cynthia Vilanova
 * Fonctionnalité: Creates a PDO connection to access the database
 * Date : 8-5-2019
 *
 * Vérification :
 * Date               Nom                   Approuvé
 *
 * Historique de modifications :
 * Date               Nom     Description
 * 8-5-2019           CV      Création
 */

class MgrDbConnection
{
    private $connection; // PDO connection object

    public function __construct()
    {
        include_once dirname(__FILE__) . '/Constants.php';
        try {
            $this->connection = new PDO("mysql:host=" . DB_HOST . ";dbname=" . DB_NAME . ";charset=utf8", DB_USER, DB_PASS);
            $this->connection->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
        } catch (PDOException $e) {
            throw new Exception("Error creating the DB Connection" . $e->getMessage());
        }
    }

    /**
     * @return PDO
     */
    public function getConnection()
    {
        return $this->connection;
    }

    /**
     * @param PDO $connection
     *
     */
    public function setConnection($connection)
    {
        $this->connection = $connection;
    }
}
