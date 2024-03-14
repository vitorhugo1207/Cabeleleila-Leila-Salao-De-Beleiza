'use server'

import { Base64 } from 'js-base64';
import { cookies } from 'next/headers'
import { redirect } from 'next/navigation'

export async function makeLogin(formData) {
    let username;
    let password;
    for (const pair of formData.entries()) {
        if (pair[0] == "username") {
            username = pair[1];
        }
        if (pair[0] == "password") {
            password = pair[1];
        }
    }

    const myHeaders = new Headers();
    myHeaders.append("Authorization", "Basic "+Base64.encode(username + ":" + password));

    const response = await fetch("http://127.0.0.1:8080/user/", {
        method: "GET",
        headers: myHeaders,
        redirect: "follow"
    }).then(response => response.text())
    .then(result => {return result})
    .catch(error => console.log('error', error));
    
    if (response != "Usuário não existe no banco de dados") {
        const json = JSON.parse(response);
        const encryptedSessionData = Base64.encode(json.apiKey);
        cookies().set('apikey', encryptedSessionData, {
            httpOnly: true,
            secure: process.env.NODE_ENV === 'production',
            maxAge: 60 * 60 * 24 * 7, // One week
            path: '/',
        });
        redirect(`/scheduling`);
    } else {
        return false;
    }

    // const a = cookies().get('apikey')?.value;
    // console.log(a ? Base64.decode(a) : null);
}
