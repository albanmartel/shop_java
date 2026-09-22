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

Exécutez la classe avec Maven :

```powershell
mvn exec:java
```

## Comment a été construit le projet

### 1. Installation de maven (sans droits administatrateur)

#### 1.0 Télécharger

[Télécharger Maven](https://maven.apache.org/download.cgi)

#### 1.1 Décompresser

Décompresser `apache-maven-3.9.16-bin.zip` dans $HOME\AppData\Local\Programs

#### 1.2 Renommer

apache-maven-3.9.16 en Apache-Maven par exemple

#### 1.3 Configurer la variable d'environnement du compte local (Windows)

Dans un PowerShell exécutez ceci :

```PowerShell
$maven = $HOME\AppData\Local\Programs\Apache-Maven"

[Environment]::SetEnvironmentVariable(
  "MAVEN_HOME",
  $maven,
  "User"
)
              
$pathUtilisateur = [Environment]::GetEnvironmentVariable("Path", "User")

if ($pathUtilisateur -notlike "*$maven\bin*") {
    [Environment]::SetEnvironmentVariable(
        "Path",
        "$pathUtilisateur;$maven\bin",
        "User"
    )
}
```

Fermer le terminal PowerShell

#### 1.4 Vérifier votre installation de Maven

Ouvrir un nouveau terminal PowerShell et vérifier votre installation :

```PowerShell
$env:MAVEN_HOME = $maven
$env:Path = "$maven\bin;$env:Path"

mvn -version
```

### 2. Utiliser maven pour construire le projet

#### 2.1 Créer un projet Maven

**Windows**

Dans PowerShell, placez-vous dans le dossier où vous souhaitez créer le projet :

```powershell
cd "$HOME\Documents"
```

Lancez la génération du projet :

```powershell
mvn archetype:generate `
  "-DgroupId=fr.exemple" `
  "-DartifactId=demo-maven" `
  "-DarchetypeArtifactId=maven-archetype-quickstart" `
  "-DarchetypeVersion=1.5" `
  "-DinteractiveMode=false"
```

**Linux**

Dans le terminal, placez-vous dans le dossier où vous souhaitez créer le projet :

```bash
cd "$HOME\Documents"
```

```Bash
mvn archetype:generate \
  -DgroupId=fr.exemple \
  -DartifactId=demo-maven \
  -DarchetypeArtifactId=maven-archetype-quickstart \
  -DarchetypeVersion=1.5 \
  -DinteractiveMode=false
```

la structure obtenue est assez semblable à celle-ci :

```text
demo-maven
├── pom.xml
└── src
    ├── main
    │   └── java
    │       └── fr
    │           └── exemple
    │               └── App.java
    └── test
        └── java
```



