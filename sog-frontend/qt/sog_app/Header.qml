/******************************************************************************************************
 ** This file has been created by ERGOSIGN GmbH - All rights reserved - http://www.ergosign.de
 ** DO NOT ALTER OR REMOVE  THIS COPYRIGHT NOTICE OR THIS FILE HEADER.
 **
 ** This file and the code contained in it are subject to the agreed contractual terms and conditions,
 ** in particular with regard to resale and publication.
 ******************************************************************************************************/
import QtQuick 2.12
import QtQuick.Layouts 1.3
import QtQuick.Controls 2.5

Rectangle
{
    id: root

    property alias title: title.text
    property alias icon: icon.icon
    property alias nextButton_icon: nextButton.icon
    property alias nextButton_enabled: mouseArea.enabled

    signal next()

    RowLayout
    {
        height: parent.height
        anchors.left: parent.left
        anchors.right: parent.right
        anchors.verticalCenter: parent.verticalCenter

        Icon
        {
            id: icon
            Layout.preferredWidth: icon.width
        }

        Text
        {
            id: title
            text: "Title"
            Layout.fillWidth: true
        }

        Rectangle
        {
            id: button
            opacity: mouseArea.enabled ? 1.0 : 0.4
            color: "lightGrey"
            Layout.fillHeight: true
            Layout.margins: 20
            Layout.preferredWidth: 100

            Icon
            {
                id: nextButton
                anchors.centerIn: parent
                color: "black"
            }

            MouseArea
            {
                id: mouseArea
                anchors.fill: parent
                onClicked: root.next()
            }
        }
    }


}
