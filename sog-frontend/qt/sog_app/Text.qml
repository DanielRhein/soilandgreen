/******************************************************************************************************
 ** This file has been created by ERGOSIGN GmbH - All rights reserved - http://www.ergosign.de
 ** DO NOT ALTER OR REMOVE  THIS COPYRIGHT NOTICE OR THIS FILE HEADER.
 **
 ** This file and the code contained in it are subject to the agreed contractual terms and conditions,
 ** in particular with regard to resale and publication.
 ******************************************************************************************************/
import QtQuick 2.12

Text
{

    font.family: fonts.fontName

    elide: Text.ElideRight
    maximumLineCount: 1
    wrapMode: Text.WordWrap
    verticalAlignment: Text.AlignVCenter

    Fonts
    {
        id: fonts
    }
}
