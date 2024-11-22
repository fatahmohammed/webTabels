@SmokeFeature
Feature: Gestion d'un tableau dynamique des employés.

  En tant qu'utilisateur, je veux pouvoir tester l'ajout, la modification et la suppression d'employés
  dans un tableau dynamique, y compris des cas valides et non valides,
  pour vérifier le bon fonctionnement et la gestion des erreurs.

  Background:
    Given L'utilisateur est sur la page de gestion des employés

  @ValidationCritique
  Scenario: Ajouter un employé avec des informations valides
    When L'utilisateur clique sur "Add"
    And L'utilisateur remplit le formulaire avec des informations :
      | First Name  | Last Name            | Email                |Age         |Salary      |Department  |
      | FATAH       | Mohammed             | fatah.qa85@gmail.com |39          |3000        | 95200      |
    And L'utilisateur "soumet" le formulaire
    Then Le nouvel "employé"  doit apparaître dans le tableau
    And Les "informations de l'employé" dans le tableau doivent correspondre à celles saisies dans le formulaire
    And Le tableau doit se mettre à jour en temps réel sans rechargement de la page

  @ValidationErreur
  Scenario: Ajouter un employé avec des informations non valides
    When L'utilisateur clique sur "Add"
    And L'utilisateur remplit le formulaire avec des informations :
      | First Name  | Last Name            | Email                |Age         |Salary      |Department  |
      |             |                      |                      |25         |3000        | 95200      |
    And L'utilisateur "soumet" le formulaire
    Then Des "messages d'erreur" doivent s'afficher pour chaque champ manquant ou invalide
    And Aucun "employé" ne doit être ajouté au tableau
    And Le tableau doit rester inchangé

  @ValidationErreur
  Scenario: Ajouter un employé avec des informations non valides
    When L'utilisateur clique sur "Add"
    And L'utilisateur remplit le formulaire avec des informations :
      | First Name  | Last Name            | Email                |Age         |Salary      |Department  |
      |      zelie       |      guerchais                | zelie.guerchais@niji.fr   |         |       |      |
    And L'utilisateur "soumet" le formulaire
    Then Des "messages d'erreur" doivent s'afficher pour chaque champ manquant ou invalide
    And Aucun "employé" ne doit être ajouté au tableau
    And Le tableau doit rester inchangé

  @ModificationCritique
  Scenario: Modifier un employé existant avec des informations valides
     When L'utilisateur clique sur l'icône d'édition pour l'employé suivant
      | First Name  | Last Name            | Email                |Age         |Salary      |Department  |
      | FATAH       |Mohammed                 | fatah.qa85@gmail.com |39          |3000         | 95200     |
    And L'utilisateur remplit le formulaire avec des informations :
      | First Name  | Last Name            | Email                |Age         |Salary      |Department  |
      | FATAH       | Mohammed                  | fatah.qa85@gmail.com |55          |3000         | 95200      |
    And L'utilisateur "soumet" le formulaire
    Then Les mise a jour sur l'employé doit apparaître dans le tableau
    And Les "informations de l'employé" dans le tableau doivent correspondre à celles saisies dans le formulaire
    And Le tableau doit se mettre à jour en temps réel sans rechargement de la page

  @ModificationErreur
  Scenario: Modifier un employé existant avec des informations non valides
   When L'utilisateur clique sur l'icône d'édition pour l'employé suivant
      | First Name  | Last Name            | Email                |Age         |Salary      |Department  |
      | FATAH       | Mohammed                  | fatah.qa85@gmail.com |55          |3000         | 95200      |
    And L'utilisateur remplit le formulaire avec des informations :
      | First Name  | Last Name            | Email                |Age         |Salary      |Department  |
      |             |      Mohammed        | fatah.qa85@gmail.com |55          |3000         | 95200      |
    And L'utilisateur "soumet" le formulaire
    Then Des "messages d'erreur" doivent s'afficher pour chaque champ manquant ou invalide
    And Les informations de l'employé ne doivent pas être mises à jour dans le tableau
    And Le tableau doit rester inchangé

  @ValidationErreur
  Scenario: Ajouter un employé avec des informations non valides
    When L'utilisateur clique sur "Add"
    And L'utilisateur remplit le formulaire avec des informations :
      | First Name  | Last Name            | Email                |Age         |Salary      |Department  |
      | DJABOURI      |                  |  |     88     |         |       |
    And L'utilisateur "soumet" le formulaire
    Then Des "messages d'erreur" doivent s'afficher pour chaque champ manquant ou invalide
    And Aucun "employé" ne doit être ajouté au tableau
    And Le tableau doit rester inchangé

  @SuppressionCritique
  Scenario: Supprimer un employé non présent dans le tableau
    When L'utilisateur clique sur l'icône supprimer pour l'employé suivant
      | First Name  | Last Name            | Email                |Age         |Salary      |Department  |
      | Cierra      | Mohammed                 | cierra@example.com |39         | 10000      | Insurance      |
    Then L'employé en question ne doit plus être présent dans le tableau
    And Le tableau doit se mettre à jour en temps réel après suppression  sans rechargement de la page

  @SuppressionCritique
  Scenario: Supprimer un employé présent dans le tableau
    When L'utilisateur clique sur l'icône supprimer pour l'employé suivant
      | First Name  | Last Name            | Email                |Age         |Salary      |Department  |
      | Cierra      | Vega                 | cierra@example.com |39         | 10000      | Insurance      |
    Then L'employé en question ne doit plus être présent dans le tableau
    And Le tableau doit se mettre à jour en temps réel après suppression  sans rechargement de la page
