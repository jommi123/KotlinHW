class Student(name: String, age: Int) : Human(name, age) {
    val records = ArrayList<CourseRecord>()

    fun addCourse(course: CourseRecord) {
        records.add(course)
    }

    fun weightedAverage() : Double {
        var gradeSum: Double = 0.0
        var totalCredits: Double = 0.0

        for (record: CourseRecord in records) {
            gradeSum += record.grade * record.credits
            totalCredits += record.credits
        }

        return gradeSum / totalCredits
    }

    fun weightedAverage(year: Int) : Double {
        var gradeSum: Double = 0.0
        var totalCredits: Double = 0.0

        for (record: CourseRecord in records) {
            if (record.yearCompleted == year)  {
                gradeSum += (record.grade * record.credits)
                totalCredits += record.credits
            }
        }
        return gradeSum / totalCredits
    }

    fun minMaxGrades(): Pair<Double, Double> {
        val min = records.minOf { it.grade }
        val max = records.maxOf { it.grade }

        return Pair(min, max)

    }

    fun checkCourse(name: String): Boolean {
        for (record in records) {
            if (record.name == name) {
                return true
            }
        }
        return false
    }


}

