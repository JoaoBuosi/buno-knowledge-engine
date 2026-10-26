# 03 — Buno Knowledge Engine
Ingestão de documentos e recuperação textual no PostgreSQL; endpoint `/api/knowledge/ask` passa o contexto recuperado ao Buno Aegis Runtime.
Suba com `docker compose up --build`; API `http://localhost:8103`.
- `POST /api/knowledge/documents` — `{"title":"Política de suporte","content":"Clientes podem abrir chamados..."}`
- `GET /api/knowledge/documents`
- `POST /api/knowledge/ask` — `{"question":"Como abrir um chamado?"}`
Importante: a recuperação é correspondência textual, não embeddings nem busca vetorial. O contrato HTTP do Runtime precisa ser ajustado ao Aegis Runtime10 real. Para produção, use chunking, embeddings, pgvector, ACL por documento, citações verificáveis e proteção contra prompt injection.
