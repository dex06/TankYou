#Installation
```mvn install```
#Execution
```mvn exec:java -pl engine```
#Jar Exécutable
Le jar exécutable se trouve dans le dossier "out/artifacts/projet_jar"

```java -jar projet.jar```

#Création d'un fichier jar
On se place dans le dossier contenant les fichiers à compiler

```jar  -cvf MyJarFile.jar *.class```



# Projet Programmation avancée 2019-2020

## Description  
Le but de ce projet est d'écrire un petit jeu dont la majorité des fonctionnalités sera implémentée sous forme
de plugins. La partie graphique utilisera OpenJFX.


### Gameplay
Une partie du jeu se déroule de la façon suivante :
   * Un nombre variable de joueurs se déplace à l'écran
   * Chaque joueur a une barre de vie
   * Une collision entre joueurs leur fait perdre de la vie
   * Le gagnant est le dernier à avoir encore de la vie
De base tous les joueurs sont controlés par l'ordinateur, sans intervention humaine


Voici la liste exhaustive des plugins présent dans le projet : 

* Background : 
    * Ajout d'une image en fond dans le jeu. Il y a la possibilité de choisir entre 6 bakcground différents.

* Collision :   
    * Plugin qui ajoute les collisions entre les joueurs et avec les potentiels obstacles. 
    
* Obstacle : 
    * Plugin que ajoute des obstacles au début de la partie. Les obstacles sont placés aléatoirement, il y plusieurs design disponibles pour ces derniers. 
    
* Statistiques : 
    * Permet d'afficher à la fin de la partie les statistiques de cette dernière. Comme le nombre de tirs éffectué par joueur ainsi que la distance parcourue. 
    
* Graphique :
    * Dessine et affiche les joueurs dans le jeu. Il y a 6 skin de personnages disponibles. 

* Menu : 
    * Ajoute une barre de menu au jeu. Cela permet de soit mettre en pause la partie, soit finir la partie et afficher les statistiques si le plugin est présent ou alours annuler al partie et revenir au paramètres de la partie. 
    
* Mouvement : 
    * Permet aux joueurs de se déplacer, il y a 5 types de mouvments différents qui vont influer sur le déplacement des joeuurs. 
    
* Arme : 
    * Ajout aux joueurs des armes permettant de tirer et ainsi faire perdre des points de vie aux adversaires. Il y a 5 armes différentes disponibles.
    
* Joueur humain :
    * Ajout la possibilité de contrôler un joueur. Cliquez sur la case IH pour attaquer le joueur humain.
    (nous n'avons pas eu le temps de corriger tous les bugs qui l'accompagnent).

