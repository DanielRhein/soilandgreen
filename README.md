# soilandgreen
Grap your shovel and pitchfork to start your gardening project.
Keep the plants healthy and plan your regular irrigation of crops.
Get tipps to start with small novice projects and become a gardening maniac.

<!--ts-->
<!--te-->

## description


- Frontend android app
- Frontend web application
- Backend  
### features

- seasonal calendar (cultivation, seeding, harvesting)
  - living room
  - glass house
  - garden

- tipps
  - garden calculator (how much plants in one patch)
  - compost create, steps 
  
- garden planner  
  - seeding planner
  - cultivation
  - harvesting
  - level (effort measured by watering, attention, conditions)
- gardening 101

### features planned
- seasonal calendar that describes cultivation, seeding and harvest of crops.
- seasonal calendar should distinguishe living room, glass house and gardening.

- application has a seasonal calender
- application allow to calculate how much plants fit into one patch
- application give tips for compost and care of plants and the effort (level)
- application should have a garden planner
- application should allow authors to edit and create informations about seeds.

- a garden planner allows to select crops
- a garden planner suggests cultivation and time to seed the crops
- a garden planner automatically suggests time from cultivation til seeding.
- a garden planner requests the garden size and calculates the patch

- an author can write about a seed. 
- an author can determine the distance between one crop to an other.
- an author can write down information about efforts, watering, attention, and conditions by glasshouse, green house and garden.
- an author gives the seeding time of the seasonal calendar
- application determines the level for the given seed. 
       
## MVP Minimal set of vialable product (Hackathon_2019)
- location selection
- show convenient crops
- season calendar
  - show harvest, sow, cultivation

Nice to have
- on bording
- combinable one each other
- Nachfolger und Vorgänger. 
  - Sommerknoblauch, was kommt danach?
- Indikator
 - daumen hoch und runter.
 - Gepflanzt oder nicht gepflanzt.


## changelog
This section contains recent changelog informations
### prepare hackathon 2019
2019/10/18 - Daniel Rhein

    - Adding some more information to the Readme.
    - Create the branch.  
    - Create an Component UML diagramm as overview
    - Describe features planned.
    - Create some pitch pics.
    - Components-devlopment-suggestion
    - er-diagramm-example added
    
### getting started with the project
2019/10/20 - Kevin Krieger; Sophie Schlegel; Daniel Rhein
     
     - Create Api-Backend with Php Symphonie (Kevin Krieger)
     - Create QT-QML-Demo-Application (Sophie Schlegel)
     - Create some documentation stuff (UMLs) (Daniel Rhein)

### make it run 
2019/10/21 - Andreas Sander, Kevin Krieger
    
    - Andreas Sander backed an react frontend for SoilAndGreen
    - Kevin Krieger finalized  Api-Backend for MVP 
        
## installation & execution
    
    ### Backend:
    - Requirements
      - PHP 7.2
      - Composer
    To run the backend you need to open sog-frontend and run composer install

    ### Frontend:
    -Requirements
       - Qt 12.5
       or React:
       - node.js
    
    To run the react-frontend you need to execute npm run start.
    To run the qt-frontend you need to execute qt-creator; open the project and execute it

## how to help

- docs
  - [User-Doc](https://github.com/DanielRhein/soilandgreen/blob/develop/src/de/soilandgreen/documentation/asciidoctor/target/generated-docs/user-documentation.pdf)
  - api documentation [Api-Doc-Link](https://github.com/DanielRhein/soilandgreen/tree/develop/sog-api)
     
  
- mailing list
  - comes up with the wiki and will be transported with the documentation
- wiki
  - comes with github
- twitter
   - need to be created within the project
   

## contribution guideline
    Create a branch that descripe your feature in the change-log section
    Keep the code clean and simple (KISS). Document your code.
    Add your name to the contributors list. Build your feature within 
    the branch. If you think your are finished, let some of the other contributors know
    about your changes. If the review has been done, you can push it to develop.
    Development-branch is the main branch. From here an release will be created into the master branch. 
## contributors
- Kevin Krieger (KK@kkrieger.de)
- Daniel Rhein (daniel.rhein84@web.de)
-  Sophia Schlegel ()
- Andreas Sander (mail@andi1984.de)
## credits
Thank for the great time [Hackathon](https://www.what-the-hack.saarland/)
Demo [Web-Application](https://github.com/DanielRhein/soilandgreen/tree/develop/sog-api)
Rest [Rest-Api](https://soilandgreen.org/api/crop)
### alternatives
(Eco-Plant)[https://github.com/Ecohackerfarm/powerplant]
