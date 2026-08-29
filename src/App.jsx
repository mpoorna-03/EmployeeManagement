import { useEffect, useState } from "react";
import "./App.css";

function App() {

  const API_URL = "http://localhost:8080/employees";
  const AI_URL = "http://localhost:8080/ai/analyze";

  const [employees, setEmployees] = useState([]);

  const [employee, setEmployee] = useState({
    name: "",
    department: "",
    email: "",
    salary: ""
  });

  const [editingId, setEditingId] = useState(null);

  // AI STATES
  const [aiReport, setAiReport] = useState("");
  const [loadingAI, setLoadingAI] = useState(false);


  // =====================================================
  // GET ALL EMPLOYEES
  // =====================================================

  const getEmployees = async () => {

    try {

      const response = await fetch(API_URL);

      if (!response.ok) {
        throw new Error("Failed to fetch employees");
      }

      const data = await response.json();

      setEmployees(data);

    } catch (error) {

      console.error(
        "Error fetching employees:",
        error
      );

    }
  };


  // =====================================================
  // LOAD EMPLOYEES WHEN PAGE OPENS
  // =====================================================

  useEffect(() => {

    getEmployees();

  }, []);


  // =====================================================
  // HANDLE INPUT
  // =====================================================

  const handleChange = (e) => {

    setEmployee({
      ...employee,
      [e.target.name]: e.target.value
    });

  };


  // =====================================================
  // ADD EMPLOYEE
  // =====================================================

  const addEmployee = async (e) => {

    e.preventDefault();

    try {

      const response = await fetch(API_URL, {

        method: "POST",

        headers: {
          "Content-Type": "application/json"
        },

        body: JSON.stringify({

          name: employee.name,

          department: employee.department,

          email: employee.email,

          salary: Number(employee.salary)

        })

      });


      if (!response.ok) {

        const error = await response.text();

        console.error(
          "Add error:",
          error
        );

        return;

      }


      alert(
        "Employee added successfully!"
      );


      clearForm();

      getEmployees();


    } catch (error) {

      console.error(
        "Error adding employee:",
        error
      );

    }

  };


  // =====================================================
  // EDIT EMPLOYEE
  // =====================================================

  const editEmployee = (emp) => {

    console.log(
      "Editing employee:",
      emp
    );


    setEditingId(emp.id);


    setEmployee({

      name: emp.name,

      department: emp.department,

      email: emp.email,

      salary: emp.salary

    });

  };


  // =====================================================
  // UPDATE EMPLOYEE
  // =====================================================

  const updateEmployee = async (e) => {

    e.preventDefault();


    console.log(
      "Updating employee ID:",
      editingId
    );

    console.log(
      "Employee data:",
      employee
    );


    try {

      const response = await fetch(
        `${API_URL}/${editingId}`,
{

  method: "PUT",

      headers: {
  "Content-Type": "application/json"
},

  body: JSON.stringify({

    name: employee.name,

    department: employee.department,

    email: employee.email,

    salary: Number(employee.salary)

  })

}
);


console.log(
    "Update status:",
    response.status
);


if (!response.ok) {

  const error = await response.text();

  console.error(
      "Update error:",
      error
  );

  alert(
      "Update failed"
  );

  return;

}


const updatedEmployee =
    await response.json();


console.log(
    "Updated employee:",
    updatedEmployee
);


alert(
    "Employee updated successfully!"
);


// Update table immediately

setEmployees(
    (previousEmployees) =>

        previousEmployees.map(
            (emp) =>

                emp.id === editingId
                    ? updatedEmployee
                    : emp
        )

);


clearForm();


} catch (error) {

  console.error(
      "Error updating employee:",
      error
  );

}

};


// =====================================================
// DELETE EMPLOYEE
// =====================================================

const deleteEmployee = async (id) => {

  try {

    const response = await fetch(
        `${API_URL}/${id}`,
        {
          method: "DELETE"
        }
    );


    if (!response.ok) {

      throw new Error(
          "Delete failed"
      );

    }


    alert(
        "Employee deleted successfully!"
    );


    getEmployees();


  } catch (error) {

    console.error(
        "Error deleting employee:",
        error
    );

  }

};


// =====================================================
// CLEAR FORM
// =====================================================

const clearForm = () => {

  setEditingId(null);


  setEmployee({

    name: "",

    department: "",

    email: "",

    salary: ""

  });

};


// =====================================================
// AI EMPLOYEE PERFORMANCE ANALYSIS
// =====================================================

const analyzePerformance = async () => {

  setLoadingAI(true);

  setAiReport("");

  try {

    const response = await fetch(
        AI_URL,
        {
          method: "POST"
        }
    );


    if (!response.ok) {

      const error =
          await response.text();

      console.error(
          "AI error:",
          error
      );

      setAiReport(
          "Failed to get AI analysis."
      );

      return;

    }


    const result =
        await response.text();


    console.log(
        "AI Response:",
        result
    );


    setAiReport(result);


  } catch (error) {

    console.error(
        "Error calling AI:",
        error
    );

    setAiReport(
        "Unable to connect to AI service. Make sure Spring Boot and Ollama are running."
    );


  } finally {

    setLoadingAI(false);

  }

};


// =====================================================
// FRONTEND
// =====================================================

return (

    <div className="container">


      <h1>
        Employee Management System
      </h1>


      {/* ==========================================
          EMPLOYEE FORM
      ========================================== */}

      <form

          onSubmit={
            editingId !== null
                ? updateEmployee
                : addEmployee
          }

          className="employee-form"

      >


        <input

            type="text"

            name="name"

            placeholder="Employee Name"

            value={employee.name}

            onChange={handleChange}

            required

        />


        <input

            type="text"

            name="department"

            placeholder="Department"

            value={employee.department}

            onChange={handleChange}

            required

        />


        <input

            type="email"

            name="email"

            placeholder="Email"

            value={employee.email}

            onChange={handleChange}

            required

        />


        <input

            type="number"

            name="salary"

            placeholder="Salary"

            value={employee.salary}

            onChange={handleChange}

            required

        />


        <button type="submit">

          {editingId !== null

              ? "Update Employee"

              : "Add Employee"}

        </button>


        {editingId !== null && (

            <button

                type="button"

                onClick={clearForm}

            >

              Cancel

            </button>

        )}

      </form>


      {/* ==========================================
          EMPLOYEE LIST
      ========================================== */}

      <h2>
        Employee List
      </h2>


      <table>

        <thead>

        <tr>

          <th>ID</th>

          <th>Name</th>

          <th>Department</th>

          <th>Email</th>

          <th>Salary</th>

          <th>Actions</th>

        </tr>

        </thead>


        <tbody>

        {employees.map(
            (emp) => (

                <tr key={emp.id}>

                  <td>
                    {emp.id}
                  </td>

                  <td>
                    {emp.name}
                  </td>

                  <td>
                    {emp.department}
                  </td>

                  <td>
                    {emp.email}
                  </td>

                  <td>
                    {emp.salary}
                  </td>

                  <td>

                    <button

                        type="button"

                        onClick={() =>
                            editEmployee(emp)
                        }

                    >

                      Edit

                    </button>


                    <button

                        type="button"

                        onClick={() =>
                            deleteEmployee(emp.id)
                        }

                    >

                      Delete

                    </button>

                  </td>

                </tr>

            )
        )}

        </tbody>

      </table>


      {/* ==========================================
          AI PERFORMANCE ANALYSIS
      ========================================== */}

      <div className="ai-section">

        <h2>
          🤖 AI Employee Performance Analysis
        </h2>


        <button

            type="button"

            onClick={analyzePerformance}

            disabled={loadingAI}

        >

          {loadingAI

              ? "Analyzing..."

              : "Analyze Employee Performance"}

        </button>


        {aiReport && (

            <div className="ai-report">

              <h3>
                AI Analysis Report
              </h3>


              <pre>
              {aiReport}
            </pre>

            </div>

        )}

      </div>


    </div>

);

}

export default App;
