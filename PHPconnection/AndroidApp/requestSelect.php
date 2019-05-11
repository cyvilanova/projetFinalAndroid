<?php
/**
 * Fichier: requestSelect.php
 * Auteur: Cynthia Vilanova
 * Fonctionnalité: Sends request to QueryEngine and json_encode the resultset for Java
 * Date : 8-5-2019 
 *
 * Vérification :
 * Date               Nom                   Approuvé
 *
 * Historique de modifications :
 * Date               Nom     Description
 * 8-5-2019           CV      Création
 */

require_once 'QueryEngine.php';

$queryEngine = new QueryEngine();

$parameters = json_decode($_POST['parameters']);

$resultSet = $queryEngine->executeQuery($_POST['query'], $parameters);

$resultArray = array();
foreach ($resultSet->fetchAll(\PDO::FETCH_NUM) as $result) {
  array_push($resultArray, $result);
}

echo json_encode($resultArray);
