class Major(val name: String) {
    val students = ArrayList<Student>()

    fun addStudent(student: Student) {
        students.add(student)
    }

    fun stats(): Triple<Double, Double, Double> {
        val smallest = students.minOf { it.weightedAverage() }
        val highest = students.maxOf { it.weightedAverage() }

        var averages = 0.0
        var amount = 0.0

        for (student: Student in students) {
            averages += student.weightedAverage()
            amount++

        }
        val average = averages / amount

        return Triple(smallest, highest, average)

    }

//    fun stats(courseName: String): Triple<Double, Double, Double> {
//        var smallest: Double
//        var highest: Double
//
//        var averages = 0.0
//        var amount = 0.0
//
//        for (student in students) {
//            if (student.checkCourse(courseName)) {
//
//            }
//        }
//
//
//
//    }

}