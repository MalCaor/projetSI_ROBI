
Installation de Java FX
-----------------------

Nécessaire à partir de Java 9

Téléchargement JavaFX 18 (avril 2022)
-------------------------------------

https://openjfx.io/
https://gluonhq.com/products/javafx/

Pour Linux
télécharger openjfx-18_linux-x64_bin-sdk.zip (Linux 18 x64 SDK)

Configuration Eclipse, accès aux jars JavaFX
--------------------------------------------

clic droit sur le projet puis
Java Build path / Libraries / Modulepath / Add external jars

ajouter les jars de javafx-sdk-18/lib

Configuration Eclipse, exécution du programme
---------------------------------------------

clic droit sur le programme à exécuter (ClientRobi.java)
puis
Run As / Run configurations
Arguments / VM arguments
--module-path "/...chemin.../javafx-sdk-18/lib" --add-modules=javafx.controls,javafx.fxml

