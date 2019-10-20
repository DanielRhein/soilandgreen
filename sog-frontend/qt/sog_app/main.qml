import QtQuick 2.12
import QtQuick.Controls 2.5

ApplicationWindow
{
    id: window
    visible: true
    width: 640
    height: 480
    title: qsTr("Stack")

    Header
    {
        id: header
        width: parent.width
        height: 80
        icon: icons.icons.flower

        nextButton_icon: icons.icons.next
        nextButton_enabled:
        {
            if(stackView.depth == 1)
                return locationSelection.gardenType >= 0
            else if (stackView.depth == 2)
                return plantSelection.isSomethingSelected
            else
                return false
        }

        onNext:
        {
            if(stackView.depth == 1)
                stackView.push(plantSelection)
            else if(stackView.depth == 2)
            {
                //MAKE THE REQUEST HERE
                console.log("Requesting plant plan for the chosen garden type: " + locationSelection.gardenType + " and the following plants: " + plantSelection.listOfSelectedIndexes)
                stackView.push(resultView)
            }
        }
    }

    StackView
    {
        id: stackView
        anchors.left: parent.left
        anchors.right: parent.right
        anchors.top: header.bottom
        anchors.bottom: parent.bottom
        initialItem: locationSelection

        LocationSelectionView
        {
            id: locationSelection
        }

        PlantSelectionView
        {
            id: plantSelection
            visible: false
        }

        ResultView
        {
            id: resultView
            visible: false
            basicJsonText: "HALLO!"
        }
    }

    Item
    {
        id: stateWrapper

        states: [
            State
            {
                name: "locationPage"
                when: stackView.depth ==1

                PropertyChanges{target: header; icon: icons.icons.location; title:qsTr("Select your garden type.")}
            },
            State
            {
                name: "plantPage"
                when: stackView.depth == 2
                PropertyChanges {target: header; icon: icons.icons.flower; title: qsTr("Choose plants you want to harvest!")}
            }
        ]
    }

    Fonts
    {
        id: fonts
    }

    Icons
    {
        id: icons
    }
}

