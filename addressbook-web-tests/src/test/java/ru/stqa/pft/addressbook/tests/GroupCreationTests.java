package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;


public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    app.goTo().groupPage();
    Groups before = app.group().all();
    app.group().initGroupCreation();
    GroupData group = new GroupData()
            .withName("test1")
            .withHeader("test2")
            .withFooter("test3");
    app.group().create(group);
    Groups after = app.group().all();

    assertThat(after.size(), equalTo(before.size()+1));

    assertThat(after, equalTo(before
            .withAdded( group.withId(after
            .stream()
            .mapToInt((g) -> g.getId())
            .max()
            .getAsInt()))));

  }

}
