# BlueTools

Work in progress
The menu bar at the bottom will be changed to have, Local Device, Remote Devices and something else.

Right now there is only the local device.  My intent is to show as many attributes about the local bluetooth as possible.

When the user clicks Remote Devices, I will show a list of devices the user can pair with. 

Right now this uses an MVP pattern. 
The only third party library is Greenbots Eventbus.

Treating the Bluetooth Adapter like a model.  This gives me the most up to date data from the adapter.

Currently in the process of adding tests for what is there now. 

ToDo: Add warning notification that "Bluetooth has been enabled for x number of minutes with no pairing.  Would you like to disable Bluetooth until you need it?"


[![BlueTools](https://github.com/bordercitycoder/BlueTools/blob/master/app/images/bluetools1_resized.gif?branch=master)](https://github.com/bordercitycoder/BlueTools/blob/master/app/images/bluetools1_resized.gif)
