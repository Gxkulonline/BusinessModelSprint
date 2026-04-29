# 🚀 Sprint Evaluation Notes — Gokul
## Entities: Employee & Office | Frontend: GokulComponent

---

# 1. ANNOTATIONS — Complete Breakdown

## 1.1 JPA (Jakarta Persistence) Annotations

| Annotation | Where Used | Purpose |
|---|---|---|
| `@Entity` | Employee, Office | Marks the class as a JPA entity (maps to a DB table) |
| `@Table(name = "employees")` | Employee | Specifies the exact table name in the database |
| `@Table(name = "offices")` | Office | Specifies the exact table name in the database |
| `@Id` | Employee (`employeeNumber`), Office (`officeCode`) | Marks the **primary key** field |
| `@Column(name = "...")` | All fields | Maps a Java field to a specific DB column name |
| `@ManyToOne` | Employee → Office, Employee → Manager | Many employees belong to **one** office; many employees report to **one** manager |
| `@OneToMany(mappedBy = "office")` | Office → employees | One office has **many** employees. `mappedBy` indicates the **inverse side** (Office does NOT own the FK) |
| `@OneToMany(mappedBy = "salesRep")` | Employee → customers | One employee (sales rep) handles **many** customers |
| `@JoinColumn(name = "officeCode")` | Employee.office | Specifies the **foreign key column** in the `employees` table that references `offices` |
| `@JoinColumn(name = "reportsTo")` | Employee.manager | FK column for the **self-referencing** relationship (employee reports to another employee) |
| `FetchType.LAZY` | Both `@OneToMany` | Data is loaded **only when accessed**, not eagerly with the parent — improves performance |

### Key Relationship Diagram
```
Office (1) ──────< (Many) Employee
Employee (1) ────< (Many) Employee  (self-join: manager → reportsTo)
Employee (1) ────< (Many) Customer  (salesRep)
```

> [!IMPORTANT]
> **`mappedBy`** is always on the **inverse (non-owning) side**. The entity that has `@JoinColumn` is the **owning side** and holds the actual FK in the DB.

---

## 1.2 Validation Annotations (`jakarta.validation.constraints`)

### In Employee Entity
| Annotation | Field | What It Does |
|---|---|---|
| `@NotNull(message = "...")` | `employeeNumber`, `office` | Field must NOT be null |
| `@NotBlank(message = "...")` | `firstName`, `lastName`, `email`, `jobTitle` | Must not be null AND must have at least one non-whitespace character |
| `@Email(message = "...")` | `email` | Must be a valid email format (e.g., `abc@xyz.com`) |

### In Office Entity
| Annotation | Field | What It Does |
|---|---|---|
| `@NotBlank(message = "...")` | `officeCode`, `city`, `phone`, `addressLine1`, `country` | Must not be null/empty |
| `@Size(min=10, max=15)` | `phone` | String length must be between 10 and 15 characters |

### In EmployeeRequestDto
| Annotation | Field | What It Does |
|---|---|---|
| `@NotNull` | `employeeNumber`, `office` | Cannot be null |
| `@NotBlank` | `firstName`, `lastName`, `email`, `jobTitle` | Cannot be empty |
| `@Email` | `email` | Valid email format |

### In OfficeRequestDto
| Annotation | Field | What It Does |
|---|---|---|
| `@NotBlank` | `officeCode`, `city`, `phone`, `addressLine1`, `country` | Cannot be empty |
| `@Pattern(regexp = "^[0-9]+$")` | `officeCode`, `phone` | Must contain **only numbers** (regex validation) |
| `@Size(min=10, max=15)` | `phone` | Length constraint |

> [!TIP]
> **`@NotNull` vs `@NotBlank`**: `@NotNull` only checks for null. `@NotBlank` checks for null, empty string `""`, AND whitespace-only `"   "`. Use `@NotBlank` for Strings, `@NotNull` for non-String types like Integer.

---

## 1.3 Spring Annotations

| Annotation | Where Used | Purpose |
|---|---|---|
| `@Service` | EmployeeServiceImpl, OfficeServiceImpl | Marks a class as a **service-layer bean** (business logic). Spring auto-detects and manages it. |
| `@RestController` | EmployeeController, OfficeController | Combines `@Controller` + `@ResponseBody`. Every method return value is automatically serialized to JSON. |
| `@RequestMapping("/employees")` | EmployeeController | Base URL path for all endpoints in this controller |
| `@GetMapping` | getAll, getById | Handles **HTTP GET** requests |
| `@PostMapping` | save | Handles **HTTP POST** requests |
| `@DeleteMapping("/{id}")` | delete | Handles **HTTP DELETE** requests |
| `@PathVariable` | getById, delete methods | Extracts `{id}` from the URL path (e.g., `/employees/1002`) |
| `@RequestBody` | save methods | Deserializes the **JSON body** of the request into a Java object |
| `@Valid` | save methods (with `@RequestBody`) | **Triggers** validation annotations on the DTO. Without this, `@NotBlank` etc. won't work! |
| `@Autowired` | Service and Repository injections | Spring **injects** the dependency automatically (Dependency Injection) |

> [!CAUTION]
> **`@Valid` is critical!** If you forget `@Valid` before `@RequestBody`, none of your validation annotations (`@NotBlank`, `@Email`, etc.) will be enforced. The request will go through even with invalid data.

---

## 1.4 Jackson Annotation

| Annotation | Where Used | Purpose |
|---|---|---|
| `@JsonIgnore` | Office.employees | **Prevents infinite recursion** during JSON serialization. Without it: Office → employees → each employee.office → Office → employees → ∞ crash! |

---

# 2. CUSTOM QUERIES IN REPOSITORIES

## 2.1 EmployeeRepository
```java
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    // JPQL Custom Query — uses @Query annotation
    @Query("SELECT e FROM Employee e WHERE e.manager.employeeNumber = :managerId")
    List<Employee> findByManagerEmployeeNumber(Integer managerId);

    // Derived Query Method — Spring auto-generates SQL from method name
    List<Employee> findByOfficeOfficeCode(String officeCode);
}
```

### Explanation:

| Method | Type | How It Works |
|---|---|---|
| `findByManagerEmployeeNumber` | **JPQL `@Query`** | You write the query manually using entity field names (not table columns). `:managerId` is a parameter placeholder. |
| `findByOfficeOfficeCode` | **Derived Query** | Spring reads the method name: `findBy` + `Office` (field in Employee) + `OfficeCode` (field in Office). It auto-generates: `SELECT * FROM employees WHERE officeCode = ?` |

> [!NOTE]
> **JPQL vs SQL**: JPQL uses **entity class names and field names** (e.g., `Employee`, `manager.employeeNumber`), NOT table/column names. Spring translates it to SQL at runtime.

## 2.2 OfficeRepository
```java
public interface OfficeRepository extends JpaRepository<Office, String> {

    // Derived Query Methods
    List<Office> findByCity(String city);
    List<Office> findByCountry(String country);
}
```

| Method | How Spring Generates SQL |
|---|---|
| `findByCity("Tokyo")` | `SELECT * FROM offices WHERE city = 'Tokyo'` |
| `findByCountry("USA")` | `SELECT * FROM offices WHERE country = 'USA'` |

### Derived Query Naming Rules:
```
findBy + FieldName → simple WHERE clause
findBy + Field1 + And + Field2 → WHERE field1 = ? AND field2 = ?
findBy + Relationship + FieldInRelatedEntity → JOIN query
```

## 2.3 Built-in JpaRepository Methods (No Code Needed!)

These come **free** from `JpaRepository<Entity, IdType>`:

| Method | What It Does | Used In |
|---|---|---|
| `findAll()` | Returns all records | `getAll()` in both services |
| `findById(id)` | Returns `Optional<Entity>` | `getById()`, `mapToEntity()` |
| `save(entity)` | INSERT or UPDATE | `save()` in both services |
| `deleteById(id)` | DELETE by primary key | `delete()` in both services |
| `existsById(id)` | Returns `boolean` | `delete()` — checks before deleting |

---

# 3. EXCEPTION HANDLING

## 3.1 Custom Exception Class
```java
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(final String message) {
        super(message);
    }
}
```

- Extends `RuntimeException` (unchecked exception — no need for `throws` in method signatures)
- Takes a custom message string

## 3.2 Where Exceptions Are Thrown

### In EmployeeServiceImpl:
```java
// 1. Employee not found by ID
Employee e = employeeRepo.findById(id)
    .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

// 2. Office is null in request
if (dto.getOffice() == null) {
    throw new IllegalArgumentException("Office cannot be null");
}

// 3. Office not found in DB
Office office = officeRepo.findById(dto.getOffice().getOfficeCode())
    .orElseThrow(() -> new ResourceNotFoundException("office not found"));

// 4. Manager not found
Employee manager = employeeRepo.findById(dto.getReportsTo())
    .orElseThrow(() -> new ResourceNotFoundException("Employee Manager not found"));

// 5. Delete — check exists first
if (!employeeRepo.existsById(id)) {
    throw new ResourceNotFoundException("Employee not found with ID");
}
```

### In OfficeServiceImpl:
```java
// 1. Office not found by ID
Office office = officeRepo.findById(id)
    .orElseThrow(() -> new ResourceNotFoundException("Office not found"));

// 2. Delete — check exists first
if (!officeRepo.existsById(id)) {
    throw new ResourceNotFoundException("Office not found with code: " + id);
}
```

## 3.3 The `orElseThrow()` Pattern

```java
// Without orElseThrow (verbose):
Optional<Employee> opt = employeeRepo.findById(id);
if (opt.isEmpty()) {
    throw new ResourceNotFoundException("Not found");
}
Employee e = opt.get();

// With orElseThrow (clean, one-liner):
Employee e = employeeRepo.findById(id)
    .orElseThrow(() -> new ResourceNotFoundException("Not found"));
```

> [!TIP]
> `orElseThrow()` uses a **lambda expression** (`() -> ...`). The lambda is a `Supplier<Exception>` — it creates the exception only if the Optional is empty.

---

# 4. SERVICE LAYER — DTO MAPPING PATTERN

## 4.1 Why DTOs?

| Without DTO | With DTO |
|---|---|
| Exposes entire entity (all fields, relationships) | Only sends necessary fields to client |
| Risk of infinite JSON recursion | No recursion issues |
| Client sees internal DB structure | Clean, controlled API response |

## 4.2 Request DTO → Entity (mapToEntity)

```java
// EmployeeServiceImpl — mapToEntity
private Employee mapToEntity(EmployeeRequestDto dto) {
    Employee e = new Employee();
    e.setEmployeeNumber(dto.getEmployeeNumber());
    e.setFirstName(dto.getFirstName());
    // ... other fields ...

    // RELATIONSHIP MAPPING: Look up Office from DB
    Office office = officeRepo.findById(dto.getOffice().getOfficeCode())
        .orElseThrow(() -> new ResourceNotFoundException("office not found"));
    e.setOffice(office);

    // OPTIONAL RELATIONSHIP: Manager
    if (dto.getReportsTo() != null) {
        Employee manager = employeeRepo.findById(dto.getReportsTo())
            .orElseThrow(() -> new ResourceNotFoundException("Manager not found"));
        e.setManager(manager);
    }
    return e;
}
```

> [!IMPORTANT]
> We don't just copy the office code — we **fetch the actual Office entity from the DB** and set it. This ensures the FK relationship is valid.

## 4.3 Entity → Response DTO (mapToDto)

```java
// EmployeeServiceImpl — mapToDto
private EmployeeResponseDto mapToDto(Employee e) {
    EmployeeResponseDto dto = new EmployeeResponseDto();
    dto.setEmployeeNumber(e.getEmployeeNumber());
    dto.setFirstName(e.getFirstName());
    dto.setLastName(e.getLastName());
    dto.setJobTitle(e.getJobTitle());
    // Note: does NOT include email, extension, office, manager
    return dto;
}
```

## 4.4 Inner DTO (Nested DTO)

```java
// Inside EmployeeRequestDto
public static class OfficeDto {
    @NotBlank(message = "Office code cannot be empty")
    private String officeCode;
    // getter, setter
}
```
- Used to accept `{ "office": { "officeCode": "1" } }` in JSON
- Keeps the request structure clean

---

# 5. CONTROLLER LAYER — REST ENDPOINTS

## 5.1 Employee Endpoints

| HTTP Method | URL | Controller Method | Status Code |
|---|---|---|---|
| GET | `/employees` | `getAll()` | 200 OK |
| GET | `/employees/{id}` | `getById(Integer id)` | 200 OK |
| POST | `/employees` | `save(EmployeeRequestDto)` | 201 CREATED |
| DELETE | `/employees/{id}` | `delete(Integer id)` | 200 OK |

## 5.2 Office Endpoints

| HTTP Method | URL | Controller Method | Status Code |
|---|---|---|---|
| GET | `/offices` | `getAll()` | 200 OK |
| GET | `/offices/{id}` | `getById(String id)` | 200 OK |
| POST | `/offices` | `save(OfficeRequestDto)` | 201 CREATED |
| DELETE | `/offices/{id}` | `delete(String id)` | 200 OK |

## 5.3 Response Format (Standardized)
```json
{
    "status": "success",
    "message": "Employees fetched successfully",
    "data": [ ... ]
}
```

## 5.4 ResponseEntity Usage
```java
// 200 OK
return ResponseEntity.ok(Map.of("status", "success", "data", result));

// 201 CREATED
return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("status", "success", "data", saved));
```

---

# 6. UNIT TESTING (EmployeeTest.java)

## 6.1 Testing Annotations

| Annotation | Purpose |
|---|---|
| `@InjectMocks` | Creates a real instance of `EmployeeServiceImpl` and injects all `@Mock` objects into it |
| `@Mock` | Creates a **fake** (mock) version of the repository. No real DB calls. |
| `@Test` | Marks a method as a test case |
| `@BeforeEach` | Runs **before every test** to set up fresh data |

## 6.2 Setup (`@BeforeEach`)
```java
MockitoAnnotations.openMocks(this);  // Activates @Mock and @InjectMocks
validator = Validation.buildDefaultValidatorFactory().getValidator(); // For validation tests
```

## 6.3 Test Cases Explained

| Test | What It Verifies | Key Concept |
|---|---|---|
| `t1_getAll` | `getAll()` returns correct size | `when().thenReturn()` |
| `t2_getById` | `getById(1)` returns correct employee | `Optional.of()` |
| `t3_notFound` | `getById(1)` throws exception when not found | `assertThrows()` |
| `t4_save` | `save(dto)` returns non-null result | Happy path |
| `t5_saveVerify` | `save()` actually calls `employeeRepo.save()` | `verify()` — checks method was called |
| `t6_officeFail` | `save()` throws when office not found in DB | Exception on relationship |
| `t7_managerSuccess` | `save()` works when manager exists | Optional relationship |
| `t8_managerFail` | `save()` throws when manager not found | Exception on optional relationship |
| `t9_mapping` | DTO mapping returns correct firstName | Response mapping verification |
| `t10_verifyFindAll` | `getAll()` actually calls `findAll()` | `verify()` |
| `t12_invalidName` | Empty firstName fails validation | Bean Validation test |
| `t13_invalidOffice` | Null office fails validation | Bean Validation test |

## 6.4 Mockito Key Methods

```java
// STUB: "When findAll is called, return this list"
when(employeeRepo.findAll()).thenReturn(List.of(employee));

// VERIFY: "Confirm that save() was actually called"
verify(employeeRepo).save(any());

// ASSERT: "Check that this throws ResourceNotFoundException"
assertThrows(ResourceNotFoundException.class, () -> service.getById(1));
```

---

# 7. FRONTEND — GokulComponent (Angular)

## 7.1 Component Structure
- **File**: `gokul.component.ts` + `gokul.component.html`
- **Standalone component** with `CommonModule` and `FormsModule`
- Handles both **Employee** and **Office** CRUD via modals

## 7.2 Services Used
```typescript
private employeeService: EmployeeService  // HTTP calls to /employees
private officeService: OfficeService       // HTTP calls to /offices
private reportService: ReportService       // HTTP calls to /reports
```

## 7.3 Modal Flow
1. User clicks a button → `openModal('EMPLOYEE', 'GET_ALL', 'All Employees')`
2. Sets `currentEntity`, `currentModalType`, `currentModalTitle`
3. If `GET_ALL` → auto-fetches data immediately
4. If `GET_ID` / `DELETE` → shows input field, waits for user to click Fetch/Delete
5. If `POST` → shows form, waits for user to submit
6. `executeAction()` → routes to `handleEmployeeActions()` or `handleOfficeActions()`

## 7.4 Key Angular Concepts Used
- **`[(ngModel)]`** → Two-way data binding
- **`*ngIf`** → Conditional rendering
- **`*ngFor`** → Loop rendering
- **`(click)`** → Event binding
- **`[type]`** → Property binding
- **`{{ }}`** → Interpolation
- **`.subscribe()`** → RxJS Observable subscription for HTTP calls

---

# 8. QUICK Q&A FOR VIVA

**Q: Why do you use DTOs instead of entities directly?**
> To control what data is exposed to the client, prevent infinite JSON recursion, and decouple the API contract from the DB structure.

**Q: What is `@JsonIgnore` and why is it on `Office.employees`?**
> It prevents infinite recursion. Office has employees, each employee has an office, which has employees... `@JsonIgnore` breaks this cycle.

**Q: Difference between `@NotNull` and `@NotBlank`?**
> `@NotNull` only rejects null. `@NotBlank` rejects null, `""`, and `"   "` (whitespace). Use `@NotBlank` for Strings.

**Q: What is `orElseThrow()`?**
> It's called on an `Optional`. If the value is present, it returns it. If empty, it throws the exception you provide via the lambda.

**Q: What does `@Valid` do?**
> It triggers the validation annotations (`@NotBlank`, `@Email`, etc.) on the request body DTO. Without it, validation won't happen.

**Q: Explain `findByOfficeOfficeCode` — how does Spring know the query?**
> Spring Data JPA parses the method name: `findBy` + `Office` (Employee's field) + `OfficeCode` (Office's field). It generates a JOIN query automatically.

**Q: What is `@ManyToOne` vs `@OneToMany`?**
> `@ManyToOne` = many of THIS entity belong to one of THAT (Employee → Office). `@OneToMany` = one of THIS has many of THAT (Office → Employees). The `@ManyToOne` side holds the FK.

**Q: Why `extends RuntimeException` and not `Exception`?**
> `RuntimeException` is unchecked — you don't need `throws` in every method signature. Spring can catch it globally.

**Q: What is the role of `@InjectMocks` vs `@Mock`?**
> `@Mock` creates a fake dependency (e.g., fake repository). `@InjectMocks` creates a real service and injects those fakes into it.

**Q: What does `verify()` do in testing?**
> It checks that a specific method on a mock was actually called during the test. E.g., `verify(repo).save(any())` confirms `save()` was invoked.

**Q: Why use `existsById()` before `deleteById()`?**
> `deleteById()` does NOT throw an exception if the ID doesn't exist — it silently does nothing. We check first to give a proper error message.

**Q: What is `FetchType.LAZY`?**
> Related data (like an office's employee list) is NOT loaded from the DB until you actually access that field. This improves performance by avoiding unnecessary queries.

**Q: What is `mappedBy` in `@OneToMany`?**
> It tells JPA "the FK is managed by the OTHER entity". `mappedBy = "office"` means the `Employee.office` field owns the relationship column.

**Q: What is `ResponseEntity`?**
> A Spring class that lets you control the HTTP response — status code, headers, and body. E.g., `ResponseEntity.status(HttpStatus.CREATED).body(data)`.

---

# 9. PROJECT ARCHITECTURE SUMMARY

```
┌─────────────────────────────────────────────────────┐
│                   ANGULAR FRONTEND                   │
│  GokulComponent → EmployeeService / OfficeService   │
│         HTTP calls to localhost:9960                 │
└──────────────────────┬──────────────────────────────┘
                       │ REST API (JSON)
┌──────────────────────▼──────────────────────────────┐
│               SPRING BOOT BACKEND                    │
│                                                      │
│  Controller Layer    @RestController                 │
│  ├─ EmployeeController  /employees                  │
│  └─ OfficeController    /offices                    │
│          │  calls                                    │
│  Service Layer       @Service                        │
│  ├─ EmployeeServiceImpl (DTO ↔ Entity mapping)      │
│  └─ OfficeServiceImpl   (DTO ↔ Entity mapping)      │
│          │  calls                                    │
│  Repository Layer    extends JpaRepository           │
│  ├─ EmployeeRepository (custom queries)             │
│  └─ OfficeRepository   (derived queries)            │
│          │                                           │
│  Entity Layer        @Entity                         │
│  ├─ Employee  (employees table)                     │
│  └─ Office    (offices table)                       │
└──────────────────────┬──────────────────────────────┘
                       │ JPA/Hibernate
              ┌────────▼────────┐
              │   PostgreSQL DB  │
              └─────────────────┘
```
