/******************************************************************************************************
 ** This file has been created by ERGOSIGN GmbH - All rights reserved - http://www.ergosign.de
 ** DO NOT ALTER OR REMOVE  THIS COPYRIGHT NOTICE OR THIS FILE HEADER.
 **
 ** This file and the code contained in it are subject to the agreed contractual terms and conditions,
 ** in particular with regard to resale and publication.
 ******************************************************************************************************/
import QtQuick 2.12
import QtQuick.Layouts 1.3

Item
{
    id: root

    property int gardenType: -1

    onGardenTypeChanged: console.log("GARDENTYPE: " + gardenType)

    ColumnLayout
    {
        anchors.fill: parent

        Item
        {
            Layout.fillHeight: true
            Layout.fillWidth: true

            Rectangle
            {
                anchors.fill: parent
                anchors.margins: 20
                color: root.gardenType === 0 ? "lightgreen" : "white"

                Row
                {
                    spacing: 20
                    anchors.verticalCenter: parent.verticalCenter

                    Icon
                    {
                        icon: icons.icons.home
                        anchors.verticalCenter: parent.verticalCenter
                    }

                    Text
                    {
                        text: "Living room"
                    }
                }
            }

            MouseArea
            {
                anchors.fill: parent

                onClicked: root.gardenType = 0
            }
        }

        Item
        {
            Layout.fillHeight: true
            Layout.fillWidth: true

            Rectangle
            {
                anchors.fill: parent
                anchors.margins: 20
                color: root.gardenType === 1 ? "lightgreen" : "white"

                Row
                {

                    anchors.verticalCenter: parent.verticalCenter
                    spacing: 20
                    Icon
                    {
                        icon: icons.icons.home
                        anchors.verticalCenter: parent.verticalCenter
                    }

                    Text
                    {
                        text: "Balcony"
                    }
                }
            }

            MouseArea
            {
                anchors.fill: parent

                onClicked: root.gardenType = 1
            }
        }

        Item
        {
            Layout.fillHeight: true
            Layout.fillWidth: true

            Rectangle
            {
                anchors.fill: parent
                anchors.margins: 20
                color: root.gardenType === 2 ? "lightgreen" : "white"

                Row
                {
                    spacing: 20
                    anchors.verticalCenter: parent.verticalCenter

                    Icon
                    {
                        icon: icons.icons.home
                        anchors.verticalCenter: parent.verticalCenter
                    }

                    Text
                    {
                        text: "Garden"
                    }
                }
            }

            MouseArea
            {
                anchors.fill: parent

                onClicked: root.gardenType = 2
            }
        }

        Item
        {
            Layout.fillHeight: true
            Layout.fillWidth: true

            Rectangle
            {
                anchors.fill: parent
                anchors.margins: 20
                color: root.gardenType === 3 ? "lightgreen" : "white"

                Row
                {
                    spacing: 20
                    anchors.verticalCenter: parent.verticalCenter

                    Icon
                    {
                        icon: icons.icons.home
                        anchors.verticalCenter: parent.verticalCenter
                    }

                    Text
                    {
                        text: "Greenhouse"
                    }
                }
            }

            MouseArea
            {
                anchors.fill: parent

                onClicked: root.gardenType = 3
            }
        }
    }

    Icons
    {
        id: icons
    }
}
