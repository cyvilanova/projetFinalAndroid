<?php
/**
 * Fichier: requestUpdate.php
 * Auteur: Cynthia Vilanova
 * Fonctionnalité: Sends request to QueryEngine to either update, insert or delete
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

$queryEngine->executeQuery($_POST['query'], $parameters);
