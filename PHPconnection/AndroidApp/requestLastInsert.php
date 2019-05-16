<?php
/**
 * Fichier: requestSelect.php
 * Auteur: Cynthia Vilanova
 * Fonctionnalité: Sends request to QueryEngine and json_encode the resultset for Java
 * Date : 15-5-2019 
 *
 * Vérification :
 * Date               Nom                   Approuvé
 *
 * Historique de modifications :
 * Date               Nom     Description
 * 15-5-2019          CV      Création
 */

require_once 'QueryEngine.php';

$queryEngine = new QueryEngine();

$resultSet = $queryEngine->getLastInsertedId();

echo json_encode($resultSet);
