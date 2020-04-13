/******************************************************************************************************
 ** This file has been created by ERGOSIGN GmbH - All rights reserved - http://www.ergosign.de
 ** DO NOT ALTER OR REMOVE  THIS COPYRIGHT NOTICE OR THIS FILE HEADER.
 **
 ** This file and the code contained in it are subject to the agreed contractual terms and conditions,
 ** in particular with regard to resale and publication.
 ******************************************************************************************************/
import QtQuick 2.12
import QtQuick.Controls 2.5

Item
{
    id: root

    property bool isSomethingSelected: false
    property var listOfSelectedIndexes: []

    function addIndex(plant)
    {
        listOfSelectedIndexes.push(plant)

        if(listOfSelectedIndexes.length > 0)
            root.isSomethingSelected = true
        else
            root.isSomethingSelected = false
    }

    function removeIndex(plant)
    {
        listOfSelectedIndexes.pop(plant)

        if(listOfSelectedIndexes.length > 0)
            root.isSomethingSelected = true
        else
            root.isSomethingSelected = false
    }

    ListView
    {
        id: listView
        anchors.fill: parent
        model: 15

        delegate: Item
        {
            height: 40
            width: parent.width

            CheckBox
            {
                text: "Plant " + index

                onCheckedChanged:
                {
                    if(checked)
                        root.addIndex(text)
                    else
                        root.removeIndex(text)
                }
            }

            Rectangle
            {
                anchors.bottom: parent.bottom
                color: "lightgrey"
                width: parent.width
                height: 1
            }
        }
    }
}
