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

        onNext:
        {
            if(stackView.depth == 1)
                stackView.push(plantSelection)
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
    }

    Component
    {
        id: locationSelection

        Rectangle
        {
            color: "green"

        }
    }

    Component
    {
        id: plantSelection

        Rectangle
        {
            color: "pink"
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

