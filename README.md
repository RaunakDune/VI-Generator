VI-Generator
============

Hi !

Welcome to our Vegetation Index Generator ! Although it provides limited functionality as of now, we hope to build it into a much more elaborate affair.

This file serves as a simple guide to the tool. Let us go through the menus and see what they do.
_________________________________________________________________________________________________________
|--------|
|  FILE  |
|--------|
Contains the quit option. We hope you'll stay.
_________________________________________________________________________________________________________
|---------|
|  IMAGE  |
|---------|
Select the images from which the indices are to be generated. All images must be in TIFF format.

All images are opened as read-only. No image will be modified from this tool.

You can set as many images as you want. Although, be warned, selecting more than one image of the same type disregards the previous choice.

Make sure to select the correct type of multispectral image for your requirement.
For instance, EVI cannot be run on LISS III images as they do not have the BLUE band.
_________________________________________________________________________________________________________
|---------|
|  TOOLS  |
|---------|

|Currently Set Images|
|-> Here, you can check which images have currently been selected i.e. on which images the indexes will be run.
Be warned, if you select both multiband and single band images for any index, the multiband images will take priority.

|View Set Images|
|-> Display each currently selected image in a separate window, and their location.

|Clear All Images|
|-> Removes all selected images from memory. This frees you to select single band imagery for any index where multi band imagery were previously selected. Displayed Images will remain open.
_________________________________________________________________________________________________________
|-----------|
|  INDICES  |
|-----------|
After setting the images from the IMAGE menu, you can select what index is to be run from here.

You can generate as many indices as you want concurrently. This is useful for comparing, say, multi-sensor or multi-temporal NDVI values to compare land use.

Some indices, such as SAVI require input parameters from the user. You can choose to enter them yourself or continue with the default values displayed. Be warned, selecting random values may lead to higly irregular output.

Whenever an Index is successfully generated, you will be given the choice to save it. If you continue, you can browse to a location of your choice and enter the name of the file to be saved. All images will be saved in the .TIF (TAGGED IMAGE FILE) format.

Certain indexes (NDVI) will also generate an image using a standard Look-Up Table.
_________________________________________________________________________________________________________
|--------|
|  HELP  |
|--------|

|Usage|
|-> Contains this Usage File.

|Vegetation Indices|
|-> Contains a file showing commonly used vegetation indices and their formulae.

|About Us|
|-> Our contact details are included. We would love any and all feedback !
_________________________________________________________________________________________________________
