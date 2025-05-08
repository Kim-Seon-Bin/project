fetch('/api/clients')
  .then(response => response.json())
  .then(data => {
    const list = document.getElementById('client-list');
    data.forEach(client => {
      const card = document.createElement('div');
      card.className = 'client-card';
      card.innerHTML = `
        <h2>${client.clientName}</h2>
        <p><strong>주소:</strong> ${client.clientAddress}</p>
        <p><strong>전화번호:</strong> ${client.clientPhone}</p>
      `;
      list.appendChild(card);
    });
  });
