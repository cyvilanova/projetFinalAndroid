<?php
/**
 * Fichier: QueryEngine.php
 * Auteur: Cynthia Vilanova
 * Fonctionnalité: Execute a query and returns its result
 * Date : 8-5-2019
 *
 * Vérification :
 * Date               Nom                   Approuvé
 *
 * Historique de modifications :
 * Date               Nom     Description
 * 8-5-2019           CV      Création
 */

require_once 'MgrDbConnection.php';

class QueryEngine
{
    private $db; //MgrDbConnection object

    public function __construct()
    {
        $this->db = new MgrDbConnection();
    }

    public function executeQuery($queryString, $parametersMap = [])
    {
        $conn = $this->db->getConnection();
        $query = $queryString;
        $loading = $conn->prepare($query);

        foreach ($parametersMap as $key => $value) {
            $loading->bindValue($key, $value);
        }

        try {
            if (!$loading->execute()) {
                throw new Exception("Error while trying to execute the query: " . $queryString);
            } else {
                return $loading;
            }
        } catch (Exception $exception) {
            return false;
        }
    }

    public function getLastInsertedId()
    {
        $conn = $this->db->getConnection();
        $last_id = $conn->lastInsertId();
        return $last_id;
    }
}
