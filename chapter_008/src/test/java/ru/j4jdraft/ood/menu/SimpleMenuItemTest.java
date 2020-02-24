package ru.j4jdraft.ood.menu;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class SimpleMenuItemTest {

    @Test
    public void whenDisplayThenOutputUsingIndentation() {
        StringBuilder builder = new StringBuilder();
        ToStringRenderer renderer = new ToStringRenderer("-", builder::append, "");
        SimpleMenuItem item = new SimpleMenuItem("Item1", null, renderer, 3);
        item.display();
        assertThat(builder.toString(), is("---Item1"));
    }

    @Test
    public void whenChooseThenExecuteAction() {
        Action action = mock(Action.class);
        SimpleMenuItem item = new SimpleMenuItem("Item1", action, null, 0);
        item.choose();
        verify(action, times(1)).execute();
    }
}
