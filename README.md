# INFOB237-Project

Vous trouverez ici les fichiers nécessaires à la réalisation du projet du cours INFOB237 - Algorithmique.

Le rapport est disponible dans le fichier [`rapport.pdf`](./rapport.pdf).

## Organisation
Vous trouverez les différents codes sources pour les solutions naïves et optimisées dans chaque dossier.

Les dossier sont organisés de la manière suivante:
- [Plante carnivore](./Plante-carnivore/) qui contient les fichiers nécessaires à la réalisation de l'exercice sur l'invasion de plante carnivore.
- [Roi des bleu](./Roi-des-bleu/) qui contient les fichiers nécessaires à la réalisation de l'exercice sur le roi des bleus.
- [Marchand](./Marchand/) qui contient les fichiers nécessaires à la réalisation de l'exercice sur le marchand.
  
## Auteurs
  
Comme expliqué dans le rapport, nous avons réalisé la réflexion algorithmique ensemble et nous avons ensuite séparé le travail pour la réalisation des codes sources et du rapport.

## Compilation

### Plante carnivore

Pour compiler le code source de la solution naïve, il suffit de se placer dans le dossier [`Plante-carnivore/`](./Plante-carnivore/) et d'exécuter la commande suivante:

#### Solution naïve

```bash
$ javac Naive.java
$ java Naive
```

#### Solution optimisée

```bash
$ javac Diviser.java
$ java Diviser
```

### Roi des bleu

Pour compiler le code source de la solution naïve, il suffit de se placer dans le dossier [`Roi-des-bleu/`](./Roi-des-bleu/) et d'exécuter la commande suivante:

#### Solution naïve

```bash
$ javac Naive.java
$ java Naive
```

#### Solution optimisée

```bash
$ javac Dynamique.java
$ java Dynamique
```

### Marchand

Pour compiler le code source de la solution naïve, il suffit de se placer dans le dossier [`Marchand/`](./Marchand/) et d'exécuter la commande suivante:

#### Solution naïve

```bash
$ javac Naive.java Items.java
$ java Naive
```

#### Solution optimisée

```bash
$ javac Glouton.java Items.java
$ java Glouton
```