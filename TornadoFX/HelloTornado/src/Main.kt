import javafx.application.Application
import javafx.collections.FXCollections
import javafx.scene.control.Button
import javafx.scene.layout.BorderPane
import tornadofx.*
import java.time.LocalDate
import java.time.Period

class Person(id: Int, name: String, birthday: LocalDate) {
	var id by property<Int>()
	fun idProperty() = getProperty(Person::id)

	var name by property<String>()
	fun nameProperty() = getProperty(Person::name)

	var birthday by property<LocalDate>()
	fun birthdayProperty() = getProperty(Person::birthday)

	//assume today is 2016-02-28
	val age: Int get() = Period.between(birthday, LocalDate.now()).years
	
	init {
		this.id = id
		this.name = name
		this.birthday = birthday
	}
}

class MyView: View() {
	
	override val root : BorderPane by fxml("gui.fxml")
	
	private val persons = FXCollections.observableArrayList(
			Person(1,"Samantha Stuart",LocalDate.of(1981,12,4)),
			Person(2,"Tom Marks",LocalDate.of(2001,1,23)),
			Person(3,"Stuart Gills",LocalDate.of(1989,5,23)),
			Person(3,"Nicole Williams",LocalDate.of(1998,8,11))
	)

	val myTable = tableview(persons) {
		column("ID", Person::idProperty)
		column("Name", Person::nameProperty)
		column("Birthday", Person::birthdayProperty)
		columnResizePolicy = SmartResize.POLICY
	}
	
	init {
		title = "XD"
		root.center = myTable
	}
}

class MyApp: App(MyView::class)

fun main(args: Array<String>) {
	Application.launch(MyApp::class.java, *args)
}