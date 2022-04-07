# Projet Synthèse SI :

---

- PASTE Theo
- LE MEN Xavier
- BOURGEAIS Clara
- MONCHICOURT Arthur

---

## Le Programme

1. Les Classes :
* Reference : classe représentant un "élément" dans le programme, contient :
    * un receiver (un GObject sur le "canvas")
    * une liste de commandes
    * une liste de Reference "enfant"
* Environement : classe environnement contenant toutes les References
* Interpreter : classe interprétant les commandes et les exécute pour le bon objet
* Command : Interface standard pour les commandes exécuté par les objets
    * AddChild : ajoute un enfant à une Reference
    * AddElement : ajoute un élément a l’environnement
    * DelElement : supprime un élément de environnement
    * NewElement : crée une instance d’élément
    * NewImage : crée une image
    * NewString : crée une zone de texte
    * SetColor : change la couleur d'un Element
    * SetDim : change les dimensions d'un Element
    * Sleep : pause pendant un certains temps
    * Translate : déplace un Element
2. Client/Serveur
/!\ A Compléter /!\

---

## Éléments Technique :

1. L'Interpreter :
l'interpréteur est le cœur de l'application. Il découpe chaque instruction en s'appelant récursivement, exécutant les commandes les plus a droite en premier puis identifie l'objet concerné et le run avec la commande.
A noter que le code continue de s’exécuter même si l'utilisateur fait une erreur

---

## Ce que nous n'avons pas pu faire

1. Object Script : L'exercice 6 mène à la création d'une Référence "script" qui apparemment permet l’exécution d'instructions, nous avons géré la création de l’objet mais pas son exécution par manque de temps

---

## Bilan

Le projet a été développé efficacement grâce a une bonne division des taches, 2 personnes la partie commande, 2 personnes pour la partie javafx et les deux parties on été fusionner sans difficulté avec git.

dépôt git en ligne : https://github.com/MalCaor/projetSI_ROBI (branch main)
