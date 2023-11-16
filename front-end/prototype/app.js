let btnGet = document.getElementById('get-posts-btn');
let pOut = document.getElementById('out-posts');

btnGet.onclick = fetchDataAndDisplay(pOut)

async function fetchDataAndDisplay(element) {
  let res = await fetch('https://f2vqpb9qja.execute-api.eu-north-1.amazonaws.com/prod/api/posts/')
  let jsonRes = await res.json();
  console.log(jsonRes);
}
