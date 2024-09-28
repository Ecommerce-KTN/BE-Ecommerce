package com.beecommerce.models.enums;

import lombok.Getter;

@Getter
public enum SpecificationOption {
    CPUSPEED("CPU Speed"),
    CPUTYPE("CPU Type"),
    SIZEMAINDISPLAY("Size (Main Display)"),
    RESOLUTIONMAINDISPLAY("Resolution (Main Display)"),
    TECHNOLOGYMAINDISPLAY("Technology (Main Display)"),
    COLORDEPTHMAINDISPLAY("Color Depth (Main Display)"),
    REARCAMERARESOLUTION("Rear Camera - Resolution (Multiple)"),
    REARCAMERAFNUMBER("Rear Camera - F Number (Multiple)"),
    REARCAMERAAUTOFOCUS("Rear Camera - Auto Focus"),
    REARCAMERAOIS("Rear Camera - OIS"),
    REARCAMERAZOOM("Rear Camera - Zoom"),
    FRONTCAMERARESOLUTION("Front Camera - Resolution"),
    FRONTCAMERAFNUMBER("Front Camera - F Number"),
    FRONTCAMERAAUTOFOCUS("Front Camera - Auto Focus"),
    MAINCAMERAFLASH("Main Camera Flash"),
    VIDEORECORDINGRESOLUTION("Video Recording Resolution"),
    SLOWMOTION("Slow Motion"),
    AVALIABLESTORAGE("Available Memory"),
    EXTERNALSTORAGE("External Memory Support"),
    NUMBEROFSIM("Number of SIM"),
    SIMSIZE("SIM size"),
    INFRA("Infra"),
    SIMSLOTTYPE("SIM Slot Type"),
    OS("OS"),
    NFC("NFC"),
    WIFIDIRECT("Wi-Fi Direct"),
    BLUETOOTHVERSION("Bluetooth Version"),
    USBVERSION("USB Version"),
    ANTPLUS("ANT+"),
    USBINTERFACE("USB Interface"),
    LOCATIONTECHNOLOGY("Location Technology"),
    EARJACK("Earjack"),
    MHL("MHL"),
    WIFI("Wi-Fi"),
    PCSYNC("PC Sync"),
    SENSOR("Sensors"),
    INTERNETUSAGETIMELTE("Internet Usage Time(LTE)"),
    INTERNETUSAGETIMEWIFI("Internet Usage Time(Wi-Fi)"),
    BATERYCAPACITY("Battery Capacity"),
    REMOVABLE("Removable"),
    TALKTIMELTE("Talk Time(LTE)"),
    STEREO("Stereo Support"),
    VIDEOPLAYBACKFORMAT("Video Playback Format"),
    VIDEOPLAYBACKRESOLUTION("Video Playback Resolution"),
    AUDIOPLAYBACKFORMAT("Audio Playback Format"),
    GEARSUPPORT("Gear Support"),
    VIDEOPLAYINGFORMAT("Video Playing Format"),
    AUDIOPLAYINGFORMAT("Audio Playing Format"),
    VIDEOPLAYINGRESOLUTION("Video Playing Resolution"),
    SAMSUNGDEXSUPPORT("Samsung DeX Support"),
    MOBILETV("Mobile TV"),
    DIMENSION("Dimension"),
    SVOICE("S Voice");

    private final String displayName;

    SpecificationOption(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static SpecificationOption fromDisplayName(String displayName) {
        for (SpecificationOption specificationOption : values()) {
            if (specificationOption.getDisplayName().equalsIgnoreCase(displayName)) {
                return specificationOption;
            }
        }
        throw new IllegalArgumentException("No SpecificationOption with display name: " + displayName);
    }
}