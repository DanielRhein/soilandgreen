import QtQuick 2.12

Rectangle
{
    color: "green"

    property alias basicJsonText: text.text

    Text
    {
        id: text
        text: qsTr("text")
        anchors.centerIn: parent
    }

}
