# Shop_Java

Projet de l'Académy Python/JAVA Facylities Multi Services

## Enoncé

Manipuler une BDD dans éclipse
A l’aide du script Shop.sql, générez votre base de données.
Ajoutez à la table des articles les occurrences de votre choix

## Objectif

Une fois votre base de donnée peuplée, il vous faudra afficher le contenu de votre table avec Eclipse.
Aussi, reprenez l’exemple vu en cours sans oublier d’ajouter le driver MariaDB (mariadb-java-client-2.3.0.jar). Votre classe Article doit contenir les mêmes données que la table en base : identifiant, description, brand et price.

## importer le projet

```
git clone https://github.com/albanmartel/shop_java.git
```

## Exécuter le projet 

*Nécessite l'installation d'`Apache Maven`*

Compilez le projet :

```powershell
mvn compile
```

## Comment a été construit le projet

### 1. Installation de maven (sans droits administatrateur)

#### 1.0 Télécharger

[Télécharger Maven](https://maven.apache.org/download.cgi)

#### 1.1 Décompresser

Décompresser `apache-maven-3.9.16-bin.zip` dans $HOME\AppData\Local\Programs

#### 1.2 Renommer

apache-maven-3.9.16 en Apache-Maven par exemple


Exécutez la classe avec Maven :

```powershell
mvn exec:java
```
