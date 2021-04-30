import com.perpetua.entities.ToDo
import com.perpetua.repository.ToDoRepository

class InMemoryToDoRepository: ToDoRepository{
    val todos = listOf<ToDo>(
        ToDo(1, "Create a repository", true),
        ToDo(2, "Start engaging with the content", true),
        ToDo(3, "Finish the tutorial", false),
        ToDo(4, "Do my own project now", false)
    )
    override fun getAllToDos(): List<ToDo> {
        return todos
    }

    override fun getTodo(id: Int): ToDo? {
        return todos.firstOrNull { it.id == id }
    }

}