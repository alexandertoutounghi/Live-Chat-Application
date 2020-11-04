package com.msgboard;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Random;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

class Msg {
  String username;
  String content;
  LocalDateTime created;
  LocalDateTime modified;
  String[] hashtags;
  Byte[] attachment;

  public Msg(String author, String content, Byte[]... attachment) {
    LocalDateTime now = LocalDateTime.now();
    this.username = author;
    this.content = content;
    this.created = now;
    this.modified = now.minusHours((new Random()).nextInt(22) + 1);
    this.hashtags =
      Pattern
        .compile("(?!\\s)#[a-zA-Z0-9]+")
        .matcher(this.content)
        .results()
        .map(MatchResult::group)
        .toArray(String[]::new);
    if (attachment.length != 0) {
      this.attachment = attachment[0];
    }
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
      ", modifiedOn=" +
      created +
      '\'' +
      ", modifiedOn=" +
      modified +
      ", hashtags=" +
      Arrays.toString(hashtags) +
      '}'
    );
  }
}
