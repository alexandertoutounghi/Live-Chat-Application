package com.msgboard;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Attachment {
  public String filename;
  public long filesize;
  public String filetype;
  public File file;

  @Override
  public String toString() {
    return (
      "Attachment{" +
      "filename='" +
      filename +
      '\'' +
      ", filesize=" +
      filesize +
      ", filetype='" +
      filetype +
      '\'' +
      ", file=" +
      file +
      '}'
    );
  }

  public Attachment(String filename, String filetype, File file) throws IOException {
    this.filename = filename;
    this.filesize = Files.size(Paths.get(filename));
    this.filetype = filetype;
    this.file = file;
  }
}
