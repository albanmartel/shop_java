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
$maven = "$HOME\AppData\Local\Programs\Apache-Maven"

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

#### 2.2. Rechercher des dépendances JAVA

**Windows**

Dans un terminal PowerShell :

```PowerShell
$search = "mariadb-java-client"

(Invoke-RestMethod "https://search.maven.org/solrsearch/select?q=$search&rows=5&wt=json").response.docs | Select-Object @{N="GroupId";E={$_.g}}, @{N="ArtifactId";E={$_.a}}, @{N="LatestVersion";E={$_.latestVersion}}
```

**Linux**

Dans un terminal Bash :
*nécessite l'intallation de* `jq` *(jq est une ligne command qui permet de parser, éditer, transformer des données en données JSON)*

```Bash
search="mariadb-java-client"

curl -s "https://search.maven.org/solrsearch/select?q=${search}&rows=5&wt=json" | \
jq -r '.response.docs[] | "\(.g) : \(.a) : \(.latestVersion)"'
```

Cela vous permet d'obtenir une réponse :

```txt
GroupId                     ArtifactId               LatestVersion
-------                     ----------               -------------
org.jumpmind.symmetric.jdbc mariadb-java-client      1.1.1
org.mariadb.jdbc            mariadb-java-client      3.5.3
org.mariadb.jdbc            mariadb-java-client-jre7 1.6.1
org.mariadb.jdbc            mariadb-java-client-jre6 1.6.1
```

#### 2.3 Ajouter des dépendances JAVA à `pom.xml`

Le `pom.xml` est le cœur de tout projet Maven. 

POM signifie **Project Object Model** — un seul fichier XML qui déclare ce qu'est votre projet (son identité), ce dont il a besoin (ses dépendances) et comment le construire (plugins et configuration). 

Maven lit ce fichier, télécharge tout ce qu'il référence depuis un dépôt et exécute la construction. Là où un projet ad hoc disperse ces informations entre des scripts shell et un dossier lib/ de JARs copiés manuellement, Maven les regroupe toutes dans un document déclaratif et versionné.

L'ajout traditionnel d'un nouvelle dépendance ce fait en général en éditant directement dans le fichier `pom.xml` et en ajoutant: 

```
<dependencies>
    <dependency>
        <groupId>org.mariadb.jdbc</groupId>
        <artifactId>mariadb-java-client</artifactId>
        <version>3.5.3</version>
    </dependency>
</dependencies>
```

**La syntaxe générale est**  :
```
mvn dependency:add-dependency -Dartifact=<groupId>:<artifactId>:<version>
```
**Notes:** La fonctionnalité `mvn dependency:add` est une fonctionnalité dans la version de développement

Exemple avec les informations récupérées dans la recherche :

`<groupId>` ==> org.mariadb.jdbc

`<artifactId>` ==> mariadb-java-client

`<groupId>` ==> 3.5.3

**Windows**

```PowerShell
mvn dependency:add-dependency "-Dartifact=org.mariadb.jdbc:mariadb-java-client:3.5.3"
```

**Linux**

```bash
mvn dependency:add-dependency -Dartifact=org.mariadb.jdbc:mariadb-java-client:3.5.3
```

#### 2.4 Installer les dépendances JAVA avec `maven`

Maven permet d'éviter de copier manuellement les fichiers `.jar` dans un dossier `lib`.

```powershell
mvn compile
```

Cette commande va notamment :

- lire le fichier `pom.xml` ;
- télécharger les dépendances absentes ;
- les placer dans `.m2\repository` ;
- compiler les fichiers Java dans `target\classes`.

#### 2.5 Voir les dépendances utilisée avec `Maven`

Vous pouvez voir les dépendances effectivement utilisées avec :

```powershell
mvn dependency:tree
```

#### 2.6 Forcer Maven a vérifier des mises-à-jour

Pour forcer Maven à vérifier les mises à jour :

```powershell
mvn clean compile
```

### 3.0 Exécuter du code JAVA avec Maven

Imaginons que vous ayez le code suivant dans un projet JAVA pour la classe APP dans le fichier `App.java` :

```java
package fr.exemple;

import org.apache.commons.lang3.StringUtils;

public class App {
    public static void main(String[] args) {
        String texte = "Bonjour Maven";

        System.out.println(StringUtils.upperCase(texte));
        System.out.println("Le texte est vide ? " + StringUtils.isBlank(texte));
    }
}
```

Compilez le projet :

```powershell
mvn compile
```

Exécutez la classe avec Maven :

```powershell
mvn exec:java
```

Résultat attendu :

```text
BONJOUR MAVEN
Le texte est vide ? false
```

### 4.0 Aller plus loin avec Maven

Compiler et exécuter en une seule commande :

```powershell
mvn compile exec:java
```

Nettoyer les fichiers générés puis reconstruire le projet :

```powershell
mvn clean compile
```

Créer un fichier `.jar` :

```powershell
mvn package
```

Le fichier sera généré dans :

```text
target\demo-maven-1.0-SNAPSHOT.jar
```

## 5.0 Le PENSE-BêTES Maven

```powershell
# Créer un projet
mvn archetype:generate ...

# Télécharger les dépendances et compiler
mvn compile

# Afficher les dépendances
mvn dependency:tree

# Exécuter le programme
mvn exec:java
```











