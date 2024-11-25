import http from 'k6/http';
import { check, sleep } from 'k6';

// Configuração do teste
export const options = {
  stages: [
    { duration: '30s', target: 10 }, // Sobe para 10 usuários em 30 segundos
    { duration: '1m', target: 20 }, // Mantém 20 usuários por 1 minuto
    { duration: '10s', target: 0 }, // Reduz gradualmente para 0 usuários
  ],
};

// Teste principal
export default function () {
  const url = 'http://api:8080/api/v1/products/listProducts';

  const res = http.get(url);

  check(res, {
    'status is 200': (r) => r.status === 200,
    'response time is less than 500ms': (r) => r.timings.duration < 500,
  });

  sleep(1); // Aguarda 1 segundo antes de iniciar a próxima requisição
}
