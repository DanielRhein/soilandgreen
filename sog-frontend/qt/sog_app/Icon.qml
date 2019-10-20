/******************************************************************************************************
 ** This file has been created by ERGOSIGN GmbH - All rights reserved - http://www.ergosign.de
 ** DO NOT ALTER OR REMOVE  THIS COPYRIGHT NOTICE OR THIS FILE HEADER.
 **
 ** This file and the code contained in it are subject to the agreed contractual terms and conditions,
 ** in particular with regard to resale and publication.
 ******************************************************************************************************/
import QtQuick 2.12

Item
{
    id: root
    width: icon.width
    height: icon.height

    /*!
        Holds the icon from the icon font loaded in Fonts.qml and specified in Icons.qml.
    */
    property alias      icon: icon.text

    /*!
        Holds the color of the icon.
    */
    property alias      color: icon.color

    /*!
        Holds the icon-size (font size for icon font)
    */
    property alias      iconSize: icon.font.pixelSize

    /*!
        Holds the icon font. This should be the icon font loaded in Fonts.qml.
    */
    property alias      iconFont: icon.font.family

    Text
    {
        id: icon
        horizontalAlignment: Text.AlignHCenter
        verticalAlignment: Text.AlignVCenter

        font.family: fonts.iconFontName
        font.pixelSize: 20
        color: "black"
    }

    Fonts
    {
        id: fonts
    }
}
