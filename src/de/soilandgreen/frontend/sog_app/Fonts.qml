/******************************************************************************************************
 ** This file has been created by ERGOSIGN GmbH - All rights reserved - http://www.ergosign.de
 ** DO NOT ALTER OR REMOVE  THIS COPYRIGHT NOTICE OR THIS FILE HEADER.
 **
 ** This file and the code contained in it are subject to the agreed contractual terms and conditions,
 ** in particular with regard to resale and publication.
 ******************************************************************************************************/
import QtQuick 2.12

QtObject
{
    id: root

    property string fontName: sourceSans_regular.name

    property string iconFontName: iconFont.name

    readonly property var sourceSans_regular: FontLoader
    {
        id: regular
        source: "Assets/SourceSansPro-Regular.ttf"
    }
    readonly property var sourceSans_bold: FontLoader
    {
        id: bold
        source: "Assets/SourceSansPro-Bold.ttf"
    }
    readonly property var sourceSans_italic: FontLoader
    {
        id: italic
        source: "Assets/SourceSansPro-RegularItalic.ttf"
    }

    readonly property var sourceSans_semibold: FontLoader
    {
        id: semibold
        source: "Assets/SourceSansPro-SemiBold.ttf"
    }

    readonly property var iconFont: FontLoader
    {
        id: iconFont
        source: "Assets/MaterialIcons-Regular.ttf"
    }
}
