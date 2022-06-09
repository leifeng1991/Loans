package com.base.devices.data;

import com.base.devices.utils.MediaFilesUtils;

import static com.base.devices.utils.OtherUtils.getContactGroupCount;

public class MediaFilesData {

    public int audio_internal;
    public int audio_external;
    public int images_internal;
    public int images_external;
    public int video_internal;
    public int video_external;
    public int download_files;
    public int contact_group;

    {
        audio_internal = MediaFilesUtils.getAudioInternal();
        audio_external = MediaFilesUtils.getAudioExternal();
        images_internal = MediaFilesUtils.getImagesInternal();
        images_external = MediaFilesUtils.getImagesExternal();
        video_internal = MediaFilesUtils.getVideoInternal();
        video_external = MediaFilesUtils.getVideoExternal();
        download_files = MediaFilesUtils.getDownloadFilesCount();
        contact_group = getContactGroupCount();

    }

}
