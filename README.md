# Concordion-Appium-Demo
A demo that automates the basic behaviour of the  default People App (Contacts) in Android 4.4. 

This was done using Concordion as specification framework and Appium as an automation library for Android.

To use it you will need to have an Appium server started on the default address (127.0.0.1:4723/wd/hub). 

You will also need to install the Android SDK and to start an Android 4.4 virtual device by the name of "my_android".

The language of your device must be English. Otherwise, some checks of the test will fail, since they expect some of the asserting messages in english.

--------------------------------------------------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------------------------------------------

Una demo que automatiza el comportamiento básico de la aplicación People (Contactos) existente por defecto en Android 4.4.

El proyecto ha sido creado usando Concordion como framework de especificación de requisitos y Appium como una librería de automatización sobre Android.

Para utilizarla necesitarás iniciar un Appium server en la dirección por defecto (127.0.0.1:4723/wd/hub). 

También necesitarás tener instalado el Android SDK y arrancar un emulador con la versión 4.4  y bajo el nombre de my_android.

El idioma de este dispositivo emulado debe de ser Inglés. De lo contrario, algunas validaciones del test fallarán, ya que esperan los mensajes a comprobar en Inglés.
