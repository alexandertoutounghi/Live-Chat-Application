package com.msgboard;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

class Msg {
  String username;
  String content;
  LocalDate created;
  LocalDate modified;
  String[] hashtags;
  Byte[] attachment;

  public Msg(String author, String content, Byte[]... attachment) {
    LocalDate now = LocalDate.now();
    this.username = author;
    this.content = content;
    this.created = now;
    this.modified = now;
    this.hashtags =
      Pattern
        .compile("(=?\\s)#[a-zA-Z0-9]+")
        .matcher(this.content)
        .results()
        .map(MatchResult::group)
        .toArray(String[]::new);
    if (attachment.length != 0) {
      this.attachment = attachment[0];
    }
  }

  public String getUsername() {
    return username;
  }

  public String getContent() {
    return content;
  }

  public LocalDate getCreated() {
    return created;
  }

  public LocalDate getModified() {
    return modified;
  }

  public String[] getHashtags() {
    return hashtags;
  }

  public Byte[] getAttachment() {
    return attachment;
  }

  @Override
  public String toString() {
    return (
      "Msg{" +
      "author='" +
      username +
      '\'' +
      ", content='" +
      content +
      '\'' +
      ", createdOn=" +
      created +
      ", modifiedOn=" +
      modified +
      ", hashtags=" +
      Arrays.toString(hashtags) +
      '}'
    );
  }
}
