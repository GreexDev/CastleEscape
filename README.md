# README - Explication projet JAVA : Castle Escape

Par Éric Rodriguez

***

## Sommaire

1. Introduction
2. Classes
3. Fonctionnement d'une partie
4. Conclusion

***

## 1. Introduction

Dans le cadre d'un devoir, j'ai réalisé un jeu en Java qui s'utilise grâce au terminal.

Il s'utilise seulement en entrant des chiffres pour sélectionner un choix.

Le but du jeu est de sortir du chateau sans mourir.

## 2. Classes

Liste exhaustive des paquetages du jeu ainsi que leurs classes :

* Game :
  * Game : Classe permettant l'appel d'Initialize, son lancement ainsi que sa fin
  * IGame : Interface de Game
  * Initialize : Classe permettant d'initialiser le château, le joueur, les monstres et les objects
  * Run : Classe "main" permettant la création d'une partie en appellant Game
* Entities :
  * Entity : Classe abstraite représentant une entité
  * IEntity : Interface de Entity
  * Monster : Classe fille de Entity représentant un monstre
  * Player : Classe fille de Entity représentant le joueur
* Items :
  * ItemAbstract : Classe abstraite représentant un object
  * IItemAbstract : Interface de ItemAbstract
  * Item : Classe fille de ItemAbstract représentant un object classique tel qu'une potion ou un bouclier
  * Weapon : Classe fille de ItemAbstract représentant une arme
  * Bag : Classe fille de ItemAbstract représentant un sac qui peut contenir un ItemAbstract
* Map :
  * Room : Classe représentant une salle du château
  * Castle : Classe représentant un château qui contient toutes les salles
* Fight :
  * Fight : Classe permettant de faire un combat entre le joueur et un monstre
  * ProcCount2Seconds : Classe fille de Thread permettant de faire un compte à rebours de 2 secondes pour qu'un monstre puisse seulement attaquer le joueur toutes les 2 secondes.

## 3. Fonctionnement d'une partie

Au début d'une partie, on commence dans une salle du château. 

On a plusieurs choix :

* ramasser les objets présents dans la salle
* utiliser les objets contenus dans le sac du joueur
* attaquer le monstre présent dans la salle
* changer de salle

Si des actions sont impossibles, comme attaquer un monstre alors qu'il n'y a aucun monstre dans la salle ou prendre un objet alors que le joueur n'a pas de sac, alors la console informera le joueur que celle-ci est impossible.

Le joueur peut utiliser des objets qu'il a dans son sac. Si l'objet est consommable et que le joueur l'utilise alors l'objet disparait et son effet est appliqué sur le joueur. Si l'objet n'est pas consommable mais qu'il a un effet alors l'effet est appliqué sur le joueur jusqu'à ce que celui pose l'objet.

Si le joueur prend une arme alors il doit l'utiliser dans son sac pour se l'affecter et bénéficier du bonus d'attaque de l'arme.

Si un monstre est présent dans une pièce alors le joueur ne peut en sortir et doit d'abord tuer le monstre pour s'échapper de la salle.

Quand le joueur attaque un monstre, il doit lui porter des coups le plus vite possible pour tuer le monstre avant que ce ne soit le monstre qui le tue. Un monstre porte un coup au joueur toutes les 2 secondes.

Pour s'échapper du château le joueur doit possèder une clé qui est cachée dans une des salles du château.

## 4. Conclusion

J'ai essayé de mettre en pratique le cours au maximum. J'ai utilisé des Vector, des interfaces, des classes abstraites et filles ainsi qu'un Thread.

Le jeu est plutôt simple et pourrait être amélioré en mettant en place un système de sauvegarde par un fichier par exemple.



