package Repository;

import org.junit.jupiter.api.Test;
import pl.edu.pg.controller.StringController;
import pl.edu.pg.repository.EntityRepo;
import org.assertj.core.api.Assertions;

import java.util.Optional;

import static org.mockito.Mockito.*;

public class StringControllerTest {
    @Test
    public void StringController_RemoveExisting() {
        EntityRepo entityRepo = mock(EntityRepo.class);
        doNothing().when(entityRepo).delete("xyz");

        StringController controller = new StringController(entityRepo);
        String out = controller.delete("xyz");

        Assertions.assertThat(out).isEqualTo("done");
    }

    @Test
    public void StringController_RemoveNotExisting() {
        EntityRepo entityRepo = mock(EntityRepo.class);
        doThrow(new IllegalArgumentException()).when(entityRepo).delete("xyz");

        StringController controller = new StringController(entityRepo);
        String out = controller.delete("xyz");

        Assertions.assertThat(out).isEqualTo("not found");
    }

    @Test
    public void StringController_FindNotExisting() {
        EntityRepo entityRepo = mock(EntityRepo.class);
        when(entityRepo.find("xyz")).thenReturn(Optional.empty());

        StringController controller = new StringController(entityRepo);
        String out = controller.find("xyz");

        Assertions.assertThat(out).isEqualTo("not found");
    }

    @Test
    public void StringController_FindExisting() {
        EntityRepo entityRepo = mock(EntityRepo.class);
        when(entityRepo.find("xyz")).thenReturn(Optional.of("abc"));

        StringController controller = new StringController(entityRepo);
        String out = controller.find("xyz");

        Assertions.assertThat(out).isNotEqualTo(null);
    }

    @Test
    public void StringController_SaveNotExisting() {
        EntityRepo entityRepo = mock(EntityRepo.class);
        doNothing().when(entityRepo).save("xyz","abc");

        StringController controller = new StringController(entityRepo);
        String out = controller.save("xyz","abc");

        Assertions.assertThat(out).isEqualTo("done");
    }

    @Test
    public void StringController_SaveExisting() {
        EntityRepo entityRepo = mock(EntityRepo.class);
        doThrow(new IllegalArgumentException()).when(entityRepo).save("xyz","abc");

        StringController controller = new StringController(entityRepo);
        String out = controller.save("xyz","abc");

        Assertions.assertThat(out).isEqualTo("bad request");
    }
}
