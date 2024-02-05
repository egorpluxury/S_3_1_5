const form_new = document.getElementById('formForNewUser');
const create_btn = document.getElementById('closeCreateButton');
/*ААААААААААААААААААА*/ const user_table_tab= document.getElementById('user_table-tab');
const rolesSelect = document.querySelector('#roles');
form_new.addEventListener('submit', addNewUser);

async function addNewUser(event) {
    event.preventDefault();
    const urlNew = '/api/admins/newAddUser';

    let listOfRole = [];
    for (let i = 0; i < rolesSelect.selectedOptions.length; i++) {
        listOfRole.push({
            id: rolesSelect.selectedOptions[i].value
        });
    }

    let method = {
        method: 'POST',
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            username: form_new.username.value,
            lastName: form_new.lastName.value,
            age: form_new.age.value,
            email: form_new.email.value,
            password: form_new.password.value,
            roles: listOfRole
        })
    };

    try {
        const response = await fetch(urlNew, method);
        if (!response.ok) {
            throw new Error(`Server returned ${response.status} ${response.statusText}`);
        }

        form_new.reset();

        getAdminPage();

         user_table_tab.click();

        var triggerTabList = [].slice.call(document.querySelectorAll('#Admin_panel-tab a'))
        triggerTabList.forEach(function (triggerEl) {
            var tabTrigger = new bootstrap.Tab(triggerEl)

            triggerEl.addEventListener('click', function (event) {
                event.preventDefault()
                tabTrigger.show()
            })
        })
        var triggerEl = document.querySelector('#Admin_panel-tab a[href="#user_table"]');
        if (triggerEl) {
            var tabInstance = bootstrap.Tab.getInstance(triggerEl);
            if (tabInstance) {
                tabInstance.show();
            }
        }
    } catch (error) {
        console.error('Произошла ошибка при выполнении запроса:', error.message);
    }
}
const rolesElement = document.getElementById('roles');


async function getRoles() {
    try {
        const response = await fetch('/api/admins/getRoles');
        if (!response.ok) {
            throw new Error(`Server returned ${response.status} ${response.statusText}`);
        }

        const roles = await response.json();

        rolesElement.innerHTML = '';

        roles.forEach(role => {
            const option = document.createElement('option');
            option.value = role.id;
            option.text = role.name;
            rolesElement.appendChild(option);
        });
    } catch (error) {
        console.error('Произошла ошибка при получении ролей:', error.message);
    }
}

getRoles();