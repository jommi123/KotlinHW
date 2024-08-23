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

    fun stats(courseName: String): Triple<Double, Double, Double> {

        var smallest = 5.0
        var highest = 0.0

        var averagesSum = 0.0
        var count = 0.0

        for (student in students) {
            val filteredRecords = student.records.filter { it.name == courseName }

            var gradeSum = 0.0
            var total = 0.0

            for (record in filteredRecords) {
                gradeSum += record.grade * record.credits
                total += record.credits
            }

            val average = gradeSum / total

            if (average < smallest) smallest = average
            if (average > highest) highest = average

            averagesSum += average
            count++

        }
        val average = averagesSum / count
        return Triple(smallest, highest, average)

    }

}